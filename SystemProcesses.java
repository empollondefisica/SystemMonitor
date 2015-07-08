
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class SystemProcesses
{
    ObservableList<SystemProcess> Collection = null;
    TableView<SystemProcess> oTableView = null;

    public SystemProcesses()
    {
        Collection = FXCollections.observableArrayList();
    }

    public void add(SystemProcess process)
    {
        Collection.add(process);
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
        long processor,
        String owner)
    {
        SystemProcess process = new SystemProcess();

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
        process.setOwner(owner);
        process.setModified(true);

        Collection.add(process);
    }

    public void add(String fileText, String ownerName)
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
        String owner = "";
        long processor = 0;

        String pid = fileText.substring(0, fileText.indexOf(' '));
        name = fileText.substring(pid.length() + 2, fileText.indexOf(')'));
        fileText = fileText.substring(pid.length() + name.length() + 4, fileText.length());
        String[] tokens = fileText.split("\\s+");

        if(tokens.length > 1)
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
            owner       = ownerName;
        }

        add(processID,
            name,
            state,
            ppid,
            pgid,
            sessionID,
            tgid,
            minFaults,
            cMinFaults,
            majFaults,
            cMajFaults,
            utime,
            stime,
            cutime,
            cstime,
            priority,
            nice,
            threads,
            start,
            vsize,
            rss,
            processor,
            ownerName);
    }

    public void clear()
    {
        Collection.clear();
    }

    public int count()
    {
        return Collection.size();
    }

    public ObservableList<SystemProcess> getCollection()
    {
        return Collection;
    }

    public void remove(Process process)
    {
        Collection.remove(process);
    }

    public void remove(int index)
    {
        Collection.remove(index);
    }

    public int find(SystemProcess process)
    {
        return Collection.indexOf(process);
    }

    public TableView<SystemProcess> getTableView()
    {
        if(oTableView == null)
        {
            oTableView = new TableView<SystemProcess>();

            TableColumn<SystemProcess, Long> pidColumn        = new TableColumn<SystemProcess, Long>("PID");
            TableColumn<SystemProcess, String> nameColumn     = new TableColumn<SystemProcess, String>("Name");
            TableColumn<SystemProcess, String> stateColumn    = new TableColumn<SystemProcess, String>("State");
            TableColumn<SystemProcess, Long> ppidColumn       = new TableColumn<SystemProcess, Long>("PPID");
            TableColumn<SystemProcess, Long> pgidColumn       = new TableColumn<SystemProcess, Long>("PGID");
            TableColumn<SystemProcess, Long> sessionID        = new TableColumn<SystemProcess, Long>("Session");
            TableColumn<SystemProcess, Long> tgidColumn       = new TableColumn<SystemProcess, Long>("TGID");
            TableColumn<SystemProcess, Long> minFaultsColumn  = new TableColumn<SystemProcess, Long>("Minor Faults");
            TableColumn<SystemProcess, Long> cMinFaultsColumn = new TableColumn<SystemProcess, Long>("Child Minor Faults");
            TableColumn<SystemProcess, Long> majFaultsColumn  = new TableColumn<SystemProcess, Long>("Major Faults");
            TableColumn<SystemProcess, Long> cMajFaultsColumn = new TableColumn<SystemProcess, Long>("Child Major Faults");
            TableColumn<SystemProcess, Long> utimeColumn      = new TableColumn<SystemProcess, Long>("User Time");
            TableColumn<SystemProcess, Long> stimeColumn      = new TableColumn<SystemProcess, Long>("System Time");
            TableColumn<SystemProcess, Long> cutimeColumn     = new TableColumn<SystemProcess, Long>("Child User Time");
            TableColumn<SystemProcess, Long> cstimeColumn     = new TableColumn<SystemProcess, Long>("Child System Time");
            TableColumn<SystemProcess, Long> priorityColumn   = new TableColumn<SystemProcess, Long>("Priority");
            TableColumn<SystemProcess, Long> niceColumn       = new TableColumn<SystemProcess, Long>("Nice Value");
            TableColumn<SystemProcess, Long> threadColumn     = new TableColumn<SystemProcess, Long>("Num Threads");
            TableColumn<SystemProcess, Long> startColumn      = new TableColumn<SystemProcess, Long>("Start Time");
            TableColumn<SystemProcess, Long> vsizeColumn      = new TableColumn<SystemProcess, Long>("Virtual Size");
            TableColumn<SystemProcess, Long> rssColumn        = new TableColumn<SystemProcess, Long>("Resident Set Size");
            TableColumn<SystemProcess, Long> processorColumn  = new TableColumn<SystemProcess, Long>("Processor Number");
            TableColumn<SystemProcess, String> ownerColumn    = new TableColumn<SystemProcess, String>("Owner");

            pidColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("ProcessID"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, String>("Name"));
            stateColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, String>("State"));
            ppidColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("ParentPID"));
            pgidColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("ProcessGroupID"));
            sessionID.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("SessionID"));
            tgidColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("ThreadGroupID"));
            minFaultsColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("MinorFaults"));
            cMinFaultsColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("ChildMinorFaults"));
            majFaultsColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("MajorFaults"));
            cMajFaultsColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("ChildMajorFaults"));
            utimeColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("UserTime"));
            stimeColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("SystemTime"));
            cutimeColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("ChildUserTime"));
            cstimeColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("ChildSystemTime"));
            priorityColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("Priority"));
            niceColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("NiceValue"));
            threadColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("NumThreads"));
            startColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("StartTime"));
            vsizeColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("VirtualSize"));
            rssColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("ResidentSetSize"));
            processorColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("ProcessorNumber"));
            ownerColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, String>("Owner"));

            oTableView.getColumns().add(pidColumn);
            oTableView.getColumns().add(nameColumn);
            oTableView.getColumns().add(stateColumn);
            oTableView.getColumns().add(ppidColumn);
            oTableView.getColumns().add(pgidColumn);
            oTableView.getColumns().add(sessionID);
            oTableView.getColumns().add(tgidColumn);
            oTableView.getColumns().add(minFaultsColumn);
            oTableView.getColumns().add(cMinFaultsColumn);
            oTableView.getColumns().add(majFaultsColumn);
            oTableView.getColumns().add(cMajFaultsColumn);
            oTableView.getColumns().add(utimeColumn);
            oTableView.getColumns().add(stimeColumn);
            oTableView.getColumns().add(cutimeColumn);
            oTableView.getColumns().add(cstimeColumn);
            oTableView.getColumns().add(priorityColumn);
            oTableView.getColumns().add(niceColumn);
            oTableView.getColumns().add(threadColumn);
            oTableView.getColumns().add(startColumn);
            oTableView.getColumns().add(vsizeColumn);
            oTableView.getColumns().add(rssColumn);
            oTableView.getColumns().add(processorColumn);
            oTableView.getColumns().add(ownerColumn);

            oTableView.setItems(Collection);
        }

        return oTableView;
    }
}
