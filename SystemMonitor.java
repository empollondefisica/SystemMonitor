
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;

public class SystemMonitor extends Application
{
    ObservableList<Process> processList = null;
    ObservableList<CPU> cpuList = null;
    File file = null;
    
    public static void main( String[] args )
    {
        Application.launch( args );
    }
    
    public void start( Stage stage )
    {
        file = new File( "/proc" );
        processList = FXCollections.observableArrayList();
        cpuList = FXCollections.observableArrayList();
        updateCPUList();
        updateProcessList();
        
        Group group = new Group();
        Scene scene = new Scene( group, 600, 400 );
        TabPane tabPane = new TabPane();
        
        tabPane.prefWidthProperty().bind( scene.widthProperty() );
        tabPane.prefHeightProperty().bind( scene.heightProperty() );
        tabPane.setTabClosingPolicy( TabPane.TabClosingPolicy.UNAVAILABLE );
        
        tabPane.getTabs().add( buildProcessTab() );
        tabPane.getTabs().add( buildCPUTab() );
        
        group.getChildren().add( tabPane );
        
        Service<CPU> cpuService = new Service<CPU>()
        {
            Task<CPU> task = null;
            public Task<CPU> createTask()
            {
                Task<CPU> task = new Task<CPU>()
                {
                    public CPU call()
                    {
                        return null;
                    }
                };
                
                return task;
            }
        };
        
        Service<Process> processService = new Service<Process>()
        {
            Task<Process> task = null;
            public Task<Process> createTask()
            {
                Task<Process> task = new Task<Process>()
                {
                    public Process call()
                    {
                        return null;
                    }
                };
                
                return task;
            }
        };
        
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
        
        TableColumn<Process, String> nameColumn = new TableColumn<Process, String>( "Name" );
        TableColumn<Process, String> stateColumn = new TableColumn<Process, String>( "State" );
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
        tableView.getColumns().add( sizeColumn );
        tableView.getColumns().add( hwmColumn );
        tableView.getColumns().add( rssColumn );
        tableView.getColumns().add( dataColumn );
        tableView.getColumns().add( stackColumn );
        tableView.getColumns().add( exeColumn );
        tableView.getColumns().add( libColumn );
        tableView.getColumns().add( pteColumn );
        tableView.getColumns().add( threadsColumn );
        
        tableView.setItems( processList );
        
        processTab.setContent( tableView );
        
        return processTab;
    }
    
    private void updateCPUList()
    {
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
        File tempFile = null;
        File statusFile = null;
        
        for( String name : file.list() )
        {
            if( name.matches( "^\\d+$" ) )
            {
                tempFile = new File( file.getAbsoluteFile() + "/" + name );
                statusFile = new File( tempFile.getAbsoluteFile() + "/status" );
                Process proc = new Process( readProcFile( statusFile ) );
                processList.add( proc );
            }
        }
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
