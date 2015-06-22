
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Processes
{
    ObservableList<Process> Collection = null;
    TableView<Process> oTableView = null;
    
    public Processes()
    {
        Collection = FXCollections.observableArrayList();
    }
    
    public void add( Process process )
    {
        Collection.add( process );
    }
    
    public void add(
        long processID,
        String name,
        String state,
        long ppid,
        long pgid,
        long sessionID,
        long tgid,
        long minFaults,
        long cMinFaults,
        long majFaults,
        long cMajFaults,
        long utime,
        long stime,
        long cutime,
        long cstime,
        long priority,
        long nice,
        long threads,
        long start,
        long vsize,
        long rss,
        long processor )
    {
        Process process = new Process();
        
        process.setProcessID(processID);
        process.setName(name);
        process.setState(state);
        process.setParentPID(ppid);
        process.setProcessGroupID(pgid);
        process.setSessionID(sessionID);
        process.setThreadGroupID(tgid);
        process.setMinorFaults(minFaults);
        process.setChildMinorFaults(cMinFaults);
        process.setMajorFaults(majFaults);
        process.setChildMajorFaults(cMajFaults);
        process.setUserTime(utime);
        process.setSystemTime(stime);
        process.setChildUserTime(cutime);
        process.setChildSystemTime(cstime);
        process.setPriority(priority);
        process.setNiceValue(nice);
        process.setNumThreads(threads);
        process.setStartTime(start);
        process.setVirtualSize(vsize);
        process.setResidentSetSize(rss);
        process.setProcessorNumber(processor);
        process.setModified( true );
        
        Collection.add( process );
    }
    
    public void add( String fileText )
    {
        long processID = 0;
        String name = "";
        String state = "";
        long ppid = 0;
        long pgid = 0;
        long sessionID = 0;
        long tgid = 0;
        long minFaults = 0;
        long cMinFaults = 0;
        long majFaults = 0;
        long cMajFaults = 0;
        long utime = 0;
        long stime = 0;
        long cutime = 0;
        long cstime = 0;
        long priority = 0;
        long nice = 0;
        long threads = 0;
        long start = 0;
        long vsize = 0;
        long rss = 0;
        long processor = 0;
        
        String pid = fileText.substring( 0, fileText.indexOf(' ') );
        name = fileText.substring( pid.length() + 2, fileText.indexOf(')'));
        fileText = fileText.substring( pid.length() + name.length() + 4, fileText.length());
        String[] tokens = fileText.split( "\\s+" );
        
        if( tokens.length > 1 )
        {
            processID   = Long.valueOf(pid);
            state       = tokens[0];
            ppid        = Long.valueOf(tokens[1]);
            pgid        = Long.valueOf(tokens[2]);
            sessionID   = Long.valueOf(tokens[3]);
            tgid        = Long.valueOf(tokens[5]);
            minFaults   = Long.valueOf(tokens[7]);
            cMinFaults  = Long.valueOf(tokens[8]);
            majFaults   = Long.valueOf(tokens[9]);
            cMajFaults  = Long.valueOf(tokens[10]);
            utime       = Long.valueOf(tokens[11]);
            stime       = Long.valueOf(tokens[12]);
            cutime      = Long.valueOf(tokens[13]);
            cstime      = Long.valueOf(tokens[14]);
            priority    = Long.valueOf(tokens[15]);
            nice        = Long.valueOf(tokens[16]);
            threads     = Long.valueOf(tokens[17]);
            start       = Long.valueOf(tokens[19]);
            vsize       = Long.valueOf(tokens[20]);
            rss         = Long.valueOf(tokens[21]);
            processor   = Long.valueOf(tokens[36]);
        }

        add( processID, name, state, ppid, pgid, sessionID, tgid, minFaults, cMinFaults, majFaults, cMajFaults, utime, stime, cutime, cstime, priority, nice, threads, start, vsize, rss, processor);
    }
    
    public void clear()
    {
        Collection.clear();
    }
    
    public int count()
    {
        return Collection.size();
    }
    
    public ObservableList<Process> getCollection()
    {
        return Collection;
    }
    
    public void remove( Process process )
    {
        Collection.remove( process );
    }
    
    public void remove( int index )
    {
        Collection.remove( index );
    }
    
    public int find( Process process )
    {
        return Collection.indexOf( process );
    }
    
    public TableView<Process> getTableView()
    {
        if(oTableView == null)
        {
            oTableView = new TableView<Process>();
            
            TableColumn<Process, Long> pidColumn           = new TableColumn<Process, Long>( "PID" );
            TableColumn<Process, String>  nameColumn          = new TableColumn<Process, String>( "Name" );
            TableColumn<Process, String>  stateColumn         = new TableColumn<Process, String>( "State" );
            TableColumn<Process, Long> ppidColumn          = new TableColumn<Process, Long>( "PPID" );
            TableColumn<Process, Long> pgidColumn          = new TableColumn<Process, Long>( "PGID" );
            TableColumn<Process, Long> sessionID           = new TableColumn<Process, Long>( "Session" );
            TableColumn<Process, Long> tgidColumn          = new TableColumn<Process, Long>( "TGID" );
            TableColumn<Process, Long> minFaultsColumn     = new TableColumn<Process, Long>( "Minor Faults" );
            TableColumn<Process, Long> cMinFaultsColumn    = new TableColumn<Process, Long>( "Child Minor Faults" );
            TableColumn<Process, Long> majFaultsColumn     = new TableColumn<Process, Long>( "Major Faults" );
            TableColumn<Process, Long> cMajFaultsColumn    = new TableColumn<Process, Long>( "Child Major Faults" );
            TableColumn<Process, Long> utimeColumn         = new TableColumn<Process, Long>( "User Time" );
            TableColumn<Process, Long> stimeColumn         = new TableColumn<Process, Long>( "System Time" );
            TableColumn<Process, Long> cutimeColumn        = new TableColumn<Process, Long>( "Child User Time" );
            TableColumn<Process, Long> cstimeColumn        = new TableColumn<Process, Long>( "Child System Time" );
            TableColumn<Process, Long> priorityColumn      = new TableColumn<Process, Long>( "Priority" );
            TableColumn<Process, Long> niceColumn          = new TableColumn<Process, Long>( "Nice Value" );
            TableColumn<Process, Long> threadColumn        = new TableColumn<Process, Long>( "Num Threads" );
            TableColumn<Process, Long> startColumn         = new TableColumn<Process, Long>( "Start Time" );
            TableColumn<Process, Long> vsizeColumn         = new TableColumn<Process, Long>( "Virtual Size" );
            TableColumn<Process, Long> rssColumn           = new TableColumn<Process, Long>( "Resident Set Size" );
            TableColumn<Process, Long> processorColumn     = new TableColumn<Process, Long>( "Processor Number" );
            
            pidColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "ProcessID" ) );
            nameColumn.setCellValueFactory( new PropertyValueFactory<Process, String>( "Name" ) );
            stateColumn.setCellValueFactory( new PropertyValueFactory<Process, String>( "State" ) );
            ppidColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "ParentPID" ) );
            pgidColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "ProcessGroupID" ) );
            sessionID.setCellValueFactory( new PropertyValueFactory<Process, Long>( "SessionID" ) );
            tgidColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "ThreadGroupID" ) );
            minFaultsColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "MinorFaults" ) );
            cMinFaultsColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "ChildMinorFaults" ) );
            majFaultsColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "MajorFaults" ) );
            cMajFaultsColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "ChildMajorFaults" ) );
            utimeColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "UserTime" ) );
            stimeColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "SystemTime" ) );
            cutimeColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "ChildUserTime" ) );
            cstimeColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "ChildSystemTime" ) );
            priorityColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "Priority" ) );
            niceColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "NiceValue" ) );
            threadColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "NumThreads" ) );
            startColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "StartTime" ) );
            vsizeColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "VirtualSize" ) );
            rssColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "ResidentSetSize" ) );
            processorColumn.setCellValueFactory( new PropertyValueFactory<Process, Long>( "ProcessorNumber" ) );
                    
            oTableView.getColumns().add( pidColumn );
            oTableView.getColumns().add( nameColumn );
            oTableView.getColumns().add( stateColumn );
            oTableView.getColumns().add( ppidColumn );
            oTableView.getColumns().add( pgidColumn );
            oTableView.getColumns().add( sessionID );
            oTableView.getColumns().add( tgidColumn );
            oTableView.getColumns().add( minFaultsColumn );
            oTableView.getColumns().add( cMinFaultsColumn );
            oTableView.getColumns().add( majFaultsColumn );
            oTableView.getColumns().add( cMajFaultsColumn );
            oTableView.getColumns().add( utimeColumn );
            oTableView.getColumns().add( stimeColumn );
            oTableView.getColumns().add( cutimeColumn );
            oTableView.getColumns().add( cstimeColumn );
            oTableView.getColumns().add( priorityColumn );
            oTableView.getColumns().add( niceColumn );
            oTableView.getColumns().add( threadColumn );
            oTableView.getColumns().add( startColumn );
            oTableView.getColumns().add( vsizeColumn );
            oTableView.getColumns().add( rssColumn );
            oTableView.getColumns().add( processorColumn );
            
            oTableView.setItems(Collection);
        }
        
        return oTableView;
    }
}
