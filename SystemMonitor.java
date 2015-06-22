
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.stage.WindowEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.beans.property.SimpleDoubleProperty;

import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;

public class SystemMonitor extends Application
{
    Processes updateProcesses = null;
    Processes processes = null;
    CPUs updateCpus = null;
    CPUs cpus = null;
    File file = null;
    
    SimpleStringProperty interval = null;
    
    public static void main( String[] args )
    {
        Application.launch( args );
    }
    
    public void start( Stage stage )
    {
        file = new File( "/proc" );
        
        updateProcesses = new Processes();
        processes = new Processes();
        updateCpus = new CPUs();
        cpus = new CPUs();
        
        interval = new SimpleStringProperty();
        
        updateCPUList();
        updateProcessList();
        
        interval.setValue( "" + processes.count() );
        
        Group group = new Group();
        Scene scene = new Scene( group, 640, 480 );
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu( "Menu" );
        Menu procCount = new Menu();
        MenuItem exitMenuItem = new MenuItem( "Exit" );
        
        tabPane.prefWidthProperty().bind( scene.widthProperty() );
        tabPane.prefHeightProperty().bind( scene.heightProperty().subtract( menuBar.heightProperty() ) );
        tabPane.setTabClosingPolicy( TabPane.TabClosingPolicy.UNAVAILABLE );
        
        tabPane.getTabs().add( buildProcessTab() );
        tabPane.getTabs().add( buildCPUTab() );
        
        exitMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent actionEvent)
            {
                Platform.exit();
            }
        } );
        
        menuBar.getMenus().add( menu );
        menuBar.getMenus().add( procCount );
        
        menu.getItems().add( exitMenuItem );
        
        procCount.textProperty().bind( interval );
        
        borderPane.setTop( menuBar );
        borderPane.setCenter( tabPane );
        
        group.getChildren().add( borderPane );
        
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds( 2 ), new EventHandler<ActionEvent>()
        {
            public void handle( ActionEvent event )
            {
                updateProcessList();
                updateCPUList();
                
                cpus.getTableView().getColumns().get(0).setVisible(false);
                cpus.getTableView().getColumns().get(0).setVisible(true);
                processes.getTableView().getColumns().get(0).setVisible(false);
                processes.getTableView().getColumns().get(0).setVisible(true);
                                
                interval.setValue( "" + processes.count() );
            }
        }));
        
        stage.setOnCloseRequest( new EventHandler<WindowEvent>()
        {
            public void handle( WindowEvent windowEvent )
            {
                
            }
        } );
        
        timer.setCycleCount( Timeline.INDEFINITE );
        timer.play();
        
        stage.setScene( scene );
        stage.setTitle( "SystemMonitor" );
        stage.show();
    }
    
    private Tab buildCPUTab()
    {
        Tab cpuTab = new Tab( "CPU" );
        TableView<CPU> tableView = cpus.getTableView();
        
        cpuTab.setContent( tableView );
        
        return cpuTab;
    }
    
    private Tab buildProcessTab()
    {
        Tab processTab = new Tab( "Processes" );
        TableView<Process> tableView = processes.getTableView();
        
        processTab.setContent( tableView );
        
        return processTab;
    }
    
    private synchronized void updateCPUList()
    {
        updateCpus.clear();
        
        File statFile = new File( file.getAbsoluteFile() + "/stat" );
        String fileText = readProcFile( statFile );
        String[] fileLines = fileText.split( "\n" );
        CPU tempCPU = null;
        int index = -1;
        ObservableList<CPU> removeList = FXCollections.observableArrayList();
        
        for( String line : fileLines )
        {
            if( line.substring( 0, 3 ).equals( "cpu" ) )
            {
                updateCpus.add( line );
            }
        }
                
        for( CPU cpu : updateCpus.getCollection() )
        {
            index = cpus.find( cpu );
            
            if( index != -1 )
            {
                cpus.getCollection().get( index ).update(
                    cpu.getName(),
                    cpu.getUser(),
                    cpu.getNice(),
                    cpu.getSystem(),
                    cpu.getIdle(),
                    cpu.getIOWait(),
                    cpu.getInterrupts(),
                    cpu.getSoftInterrupts(),
                    cpu.getSteal() );
            }
            else
            {
                cpus.add( cpu );
            }
        }
        
        for( CPU cpu : cpus.getCollection() )
        {
            index = updateCpus.find( cpu );
            if( index == -1 )
            {
                cpu.setModified( false );
            }
        }
        
        for( CPU cpu : cpus.getCollection() )
        {
            if( !cpu.getModified() )
            {
                removeList.add( cpu );
            }
        }
        
        cpus.getTableView().setItems(cpus.getCollection());
    }
    
    private void updateProcessList()
    {
        updateProcesses.clear();
        File tempFile = null;
        File statusFile = null;
        Process tempProcess = null;
        int index = -1;
        ObservableList<Process> removeList = FXCollections.observableArrayList();
        String textFile;
        
        for( String name : file.list() )
        {
            if( name.matches( "^\\d+$" ) )
            {
                tempFile = new File( file.getAbsoluteFile() + "/" + name );
                statusFile = new File( tempFile.getAbsoluteFile() + "/stat" );
                textFile = readProcFile( statusFile );
                updateProcesses.add( textFile );
            }
        }
        
        for( Process process : updateProcesses.getCollection() )
        {
            index = processes.find( process );
            
            if( index != -1 )
            {
                tempProcess = processes.getCollection().get( index );
                tempProcess.update(
                    process.getProcessID(),
                    process.getName(),
                    process.getState(),
                    process.getParentPID(),
                    process.getProcessGroupID(),
                    process.getSessionID(),
                    process.getThreadGroupID(),
                    process.getMinorFaults(),
                    process.getChildMinorFaults(),
                    process.getMajorFaults(),
                    process.getChildMajorFaults(),
                    process.getUserTime(),
                    process.getSystemTime(),
                    process.getChildUserTime(),
                    process.getChildSystemTime(),
                    process.getPriority(),
                    process.getNiceValue(),
                    process.getNumThreads(),
                    process.getStartTime(),
                    process.getVirtualSize(),
                    process.getResidentSetSize(),
                    process.getProcessorNumber() );
            }
            else
            {
                processes.add( process );
            }
        }
        
        for( Process process : processes.getCollection() )
        {
            index = updateProcesses.find( process );
            if( index == -1 )
            {
                process.setModified( false );
            }
        }
        
        for( Process process : processes.getCollection() )
        {
            if( !process.getModified() )
            {
                removeList.add( process );
            }
            else
            {
                process.setModified( false );
            }
        }
        
        processes.getCollection().removeAll( removeList );
    }
    
    private synchronized String readProcFile( File procFile )
    {
        StringBuilder fileText = new StringBuilder();
        
        try
        {
            InputStreamReader isr = new InputStreamReader( new FileInputStream( procFile ) );
            char ch;
            int data = isr.read();
            while( data != -1 )
            {
                ch = (char)data;
                fileText.append( ch );
                data = isr.read();
            }
        }
        catch( IOException exception )
        {
            System.out.println( exception.getMessage() );
        }
        
        return fileText.toString();
    }
}
