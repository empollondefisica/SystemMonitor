
import javafx.application.Application;
import javafx.stage.Stage;
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
import javafx.beans.property.SimpleIntegerProperty;
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
    ObservableList<CPU> cpuList = null;
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
        cpuList = FXCollections.observableArrayList();
        
        interval = new SimpleStringProperty();
        
        updateCPUList();
        updateProcessList();
        
        interval.setValue( "" + ( processes.count() + cpuList.size() ) );
        
        Group group = new Group();
        Scene scene = new Scene( group, 640, 480 );
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu( "Menu" );
        Menu procCount = new Menu();
        MenuItem menuItem = new MenuItem( "Exit" );
        
        tabPane.prefWidthProperty().bind( scene.widthProperty() );
        tabPane.prefHeightProperty().bind( scene.heightProperty().subtract( menuBar.heightProperty() ) );
        tabPane.setTabClosingPolicy( TabPane.TabClosingPolicy.UNAVAILABLE );
        
        tabPane.getTabs().add( buildProcessTab() );
        tabPane.getTabs().add( buildCPUTab() );
        
        menuBar.getMenus().add( menu );
        menuBar.getMenus().add( procCount );
        
        menu.getItems().add( menuItem );
        
        procCount.textProperty().bind( interval );
        
        borderPane.setTop( menuBar );
        borderPane.setCenter( tabPane );
        
        group.getChildren().add( borderPane );
        
        final Service<Integer> cpuService = new Service<Integer>()
        {
            Task<Integer> task = null;
            public Task<Integer> createTask()
            {
                Task<Integer> task = new Task<Integer>()
                {
                    public Integer call()
                    {
                        updateCPUList();
                        return 1;
                    }
                };
                return task;
            }
        };
        
        cpuService.setOnSucceeded( new EventHandler<WorkerStateEvent>()
        {
            public void handle( WorkerStateEvent event )
            {
                cpuService.reset();
            }
        } );
        
        final Service<Integer> processService = new Service<Integer>()
        {
            Task<Integer> task = null;
            public Task<Integer> createTask()
            {
                Task<Integer> task = new Task<Integer>()
                {
                    public Integer call()
                    {
                        updateProcessList();
                        return 1;
                    }
                };
                return task;
            }
        };
        
        processService.setOnSucceeded( new EventHandler<WorkerStateEvent>()
        {
            public void handle( WorkerStateEvent event )
            {
                processService.reset();
            }
        } );
        
        Timeline timer = new Timeline(
            new KeyFrame( Duration.seconds( 10 ), new EventHandler<ActionEvent>()
        {
            public void handle( ActionEvent event )
            {
                if( ! cpuService.isRunning() )
                {
                    cpuService.start();
                }
                
                if( ! processService.isRunning() )
                {
                    processService.start();
                }
                
                interval.setValue( "" + ( processes.count() + cpuList.size() ) );
            }
        } ) );
        
        stage.setOnCloseRequest( new EventHandler<WindowEvent>()
        {
            public void handle( WindowEvent windowEvent )
            {
                if( cpuService.isRunning() )
                {
                    System.out.println( "cpuService is running" );
                }
                
                if( processService.isRunning() )
                {
                    System.out.println( "processService is running" );
                }
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
        TableView<CPU> tableView = new TableView<CPU>();
        
        TableColumn<CPU, String> nameColumn = new TableColumn<CPU, String>( "Name" );
        TableColumn<CPU, Integer> userColumn = new TableColumn<CPU, Integer>( "User" );
        TableColumn<CPU, Integer> niceColumn = new TableColumn<CPU, Integer>( "Nice" );
        TableColumn<CPU, Integer> systemColumn = new TableColumn<CPU, Integer>( "System" );
        TableColumn<CPU, Integer> idleColumn = new TableColumn<CPU, Integer>( "Idle" );
        TableColumn<CPU, Integer> iowaitColumn = new TableColumn<CPU, Integer>( "IOWait" );
        TableColumn<CPU, Integer> interruptColumn = new TableColumn<CPU, Integer>( "Interrupts" );
        TableColumn<CPU, Integer> softInterruptColumn = new TableColumn<CPU, Integer>( "SoftInterrupts" );
        TableColumn<CPU, Integer> stealColumn = new TableColumn<CPU, Integer>( "Steal" );
        
        nameColumn.setCellValueFactory( new PropertyValueFactory<CPU, String>( "Name" ) );
        userColumn.setCellValueFactory( new PropertyValueFactory<CPU, Integer>( "User" ) );
        niceColumn.setCellValueFactory( new PropertyValueFactory<CPU, Integer>( "Nice" ) );
        systemColumn.setCellValueFactory( new PropertyValueFactory<CPU, Integer>( "System" ) );
        idleColumn.setCellValueFactory( new PropertyValueFactory<CPU, Integer>( "Idle" ) );
        iowaitColumn.setCellValueFactory( new PropertyValueFactory<CPU, Integer>( "IOWait" ) );
        interruptColumn.setCellValueFactory( new PropertyValueFactory<CPU, Integer>( "Interrupts" ) );
        softInterruptColumn.setCellValueFactory( new PropertyValueFactory<CPU, Integer>( "SoftInterrupts" ) );
        stealColumn.setCellValueFactory( new PropertyValueFactory<CPU, Integer>( "Steal" ) );
        
        tableView.getColumns().add( nameColumn );
        tableView.getColumns().add( userColumn );
        tableView.getColumns().add( niceColumn );
        tableView.getColumns().add( systemColumn );
        tableView.getColumns().add( idleColumn );
        tableView.getColumns().add( iowaitColumn );
        tableView.getColumns().add( interruptColumn );
        tableView.getColumns().add( softInterruptColumn );
        tableView.getColumns().add( stealColumn );
        
        tableView.setItems( cpuList );
        
        cpuTab.setContent( tableView );
        
        return cpuTab;
    }
    
    private Tab buildProcessTab()
    {
        Tab processTab = new Tab( "Processes" );
        TableView<Process> tableView = new TableView<Process>();
        
        TableColumn<Process, String>  nameColumn = new TableColumn<Process, String>( "Name" );
        TableColumn<Process, String>  stateColumn = new TableColumn<Process, String>( "State" );
        TableColumn<Process, Integer> tgidColumn = new TableColumn<Process, Integer>( "TGID" );
        TableColumn<Process, Integer> pidColumn = new TableColumn<Process, Integer>( "PID" );
        TableColumn<Process, Integer> ppidColumn = new TableColumn<Process, Integer>( "PPID" );
        TableColumn<Process, Integer> fdSizeColumn = new TableColumn<Process, Integer>( "FDSize" );
        TableColumn<Process, Integer> peakColumn = new TableColumn<Process, Integer>( "Peak" );
        TableColumn<Process, Integer> sizeColumn = new TableColumn<Process, Integer>( "Size" );
        TableColumn<Process, Integer> hwmColumn = new TableColumn<Process, Integer>( "HWM" );
        TableColumn<Process, Integer> rssColumn = new TableColumn<Process, Integer>( "RSS" );
        TableColumn<Process, Integer> dataColumn = new TableColumn<Process, Integer>( "Data" );
        TableColumn<Process, Integer> stackColumn = new TableColumn<Process, Integer>( "Stack" );
        TableColumn<Process, Integer> exeColumn = new TableColumn<Process, Integer>( "Text" );
        TableColumn<Process, Integer> libColumn = new TableColumn<Process, Integer>( "Library" );
        TableColumn<Process, Integer> pteColumn = new TableColumn<Process, Integer>( "PTE" );
        TableColumn<Process, Integer> threadsColumn = new TableColumn<Process, Integer>( "Threads" );
        
        nameColumn.setCellValueFactory( new PropertyValueFactory<Process, String>( "Name" ) );
        stateColumn.setCellValueFactory( new PropertyValueFactory<Process, String>( "State" ) );
        tgidColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "ThreadGroupID" ) );
        pidColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "ProcessID" ) );
        ppidColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "ParentPID" ) );
        fdSizeColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "FileDescriptors" ) );
        peakColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "VmPeak" ) );
        sizeColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "VmSize" ) );
        hwmColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "VmHighWaterMark" ) );
        rssColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "VmResidentSetSize" ) );
        dataColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "VmDataSize" ) );
        stackColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "VmStackSize" ) );
        exeColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "VmExecutableSize" ) );
        libColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "VmLibrarySize" ) );
        pteColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "VmPageTableEntries" ) );
        threadsColumn.setCellValueFactory( new PropertyValueFactory<Process, Integer>( "Threads" ) );
                
        tableView.getColumns().add( pidColumn );
        tableView.getColumns().add( nameColumn );
        tableView.getColumns().add( ppidColumn );
        tableView.getColumns().add( stateColumn );
        tableView.getColumns().add( tgidColumn );
        tableView.getColumns().add( fdSizeColumn );
        tableView.getColumns().add( peakColumn );
        tableView.getColumns().add( sizeColumn );
        tableView.getColumns().add( hwmColumn );
        tableView.getColumns().add( rssColumn );
        tableView.getColumns().add( dataColumn );
        tableView.getColumns().add( stackColumn );
        tableView.getColumns().add( exeColumn );
        tableView.getColumns().add( libColumn );
        tableView.getColumns().add( pteColumn );
        tableView.getColumns().add( threadsColumn );
        
        tableView.setItems( processes.getCollection() );
        
        processTab.setContent( tableView );
        
        return processTab;
    }
    
    private void updateCPUList()
    {
        cpuList.clear();
        File statFile = new File( file.getAbsoluteFile() + "/stat" );
        String fileText = readProcFile( statFile );
        String[] fileLines = fileText.split( "\n" );
        for( String line : fileLines )
        {
            if( line.substring( 0, 3 ).equals( "cpu" ) )
            {
                cpuList.add( new CPU( line ) );
            }
        }
    }
    
    private void updateProcessList()
    {
        updateProcesses.clear();
        File tempFile = null;
        File statusFile = null;
        Process tempProcess = null;
        int index = -1;
        ObservableList<Process> removeList = FXCollections.observableArrayList();
        
        for( String name : file.list() )
        {
            if( name.matches( "^\\d+$" ) )
            {
                tempFile = new File( file.getAbsoluteFile() + "/" + name );
                statusFile = new File( tempFile.getAbsoluteFile() + "/status" );
                updateProcesses.add( readProcFile( statusFile ) );
            }
        }
        
        System.out.println( "checking " + updateProcesses.count() + " processes" );
        
        for( Process process : updateProcesses.getCollection() )
        {
            index = processes.find( process );
            
            if( index != -1 )
            {
                tempProcess = processes.getCollection().get( index );
                tempProcess.update(
                    process.getName(),
                    process.getState(),
                    process.getThreadGroupID(),
                    process.getProcessID(),
                    process.getParentPID(),
                    process.getFileDescriptors(),
                    process.getVmPeak(),
                    process.getVmSize(),
                    process.getVmHighWaterMark(),
                    process.getVmResidentSetSize(),
                    process.getVmDataSize(),
                    process.getVmStackSize(),
                    process.getVmExecutableSize(),
                    process.getVmLibrarySize(),
                    process.getVmPageTableEntries(),
                    process.getThreads() );
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
        
        System.out.println( "removing " + removeList.size() + " processes" );
        processes.getCollection().removeAll( removeList );
    }
    
    private String readProcFile( File procFile )
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
