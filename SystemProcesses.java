
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class SystemProcesses
{
    ObservableList<SystemProcess> cCollection = null;
    FilteredList<SystemProcess> cFilteredList = null;
    SortedList<SystemProcess> cSortedList = null;
    ProcessTableView cProcessTableView = null;

    public SystemProcesses()
    {
        cCollection = FXCollections.observableArrayList();
        cFilteredList = new FilteredList<SystemProcess>( cCollection, p -> true );
        cSortedList = new SortedList<SystemProcess>( cFilteredList );
        cProcessTableView = new ProcessTableView(this);

        cSortedList.comparatorProperty().bind(cProcessTableView.comparatorProperty());
        cProcessTableView.setItems(cSortedList);
    }

    public void add(SystemProcess process)
    {
        cCollection.add(process);
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

        cCollection.add(process);
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

        String pid      = fileText.substring(0, fileText.indexOf(' '));
        name            = fileText.substring(pid.length() + 2, fileText.indexOf(") "));
        fileText        = fileText.substring(pid.length() + name.length() + 4, fileText.length());
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
        cCollection.clear();
    }

    public int count()
    {
        return cCollection.size();
    }

    public ObservableList<SystemProcess> getCollection()
    {
        return cCollection;
    }

    public void remove(Process process)
    {
        cCollection.remove(process);
    }

    public void remove(int index)
    {
        cCollection.remove(index);
    }

    public int find(SystemProcess process)
    {
        return cCollection.indexOf(process);
    }

    public SortedList<SystemProcess> getSortedList()
    {
        return cSortedList;
    }

    public FilteredList<SystemProcess> getFilteredList()
    {
        return cFilteredList;
    }

    public ProcessTableView getTableView()
    {
        return cProcessTableView;
    }
}
