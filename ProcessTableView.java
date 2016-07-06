
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class ProcessTableView extends TableView<SystemProcess>
{
    private SystemProcesses oProcessCollection;
    private FilteredList<SystemProcess>    oFilteredList;
    private SortedList<SystemProcess>      oSortedList;

    private TableColumn<SystemProcess, Long>   pidColumn        = null;
    private TableColumn<SystemProcess, String> nameColumn       = null;
    private TableColumn<SystemProcess, String> stateColumn      = null;
    private TableColumn<SystemProcess, Long>   ppidColumn       = null;
    private TableColumn<SystemProcess, Long>   pgidColumn       = null;
    private TableColumn<SystemProcess, Long>   sessionColumn    = null;
    private TableColumn<SystemProcess, Long>   tgidColumn       = null;
    private TableColumn<SystemProcess, Long>   minFaultsColumn  = null;
    private TableColumn<SystemProcess, Long>   cMinFaultsColumn = null;
    private TableColumn<SystemProcess, Long>   majFaultsColumn  = null;
    private TableColumn<SystemProcess, Long>   cMajFaultsColumn = null;
    private TableColumn<SystemProcess, Long>   utimeColumn      = null;
    private TableColumn<SystemProcess, Long>   stimeColumn      = null;
    private TableColumn<SystemProcess, Long>   cutimeColumn     = null;
    private TableColumn<SystemProcess, Long>   cstimeColumn     = null;
    private TableColumn<SystemProcess, Long>   priorityColumn   = null;
    private TableColumn<SystemProcess, Long>   niceColumn       = null;
    private TableColumn<SystemProcess, Long>   threadColumn     = null;
    private TableColumn<SystemProcess, Long>   startColumn      = null;
    private TableColumn<SystemProcess, Long>   vsizeColumn      = null;
    private TableColumn<SystemProcess, Long>   rssColumn        = null;
    private TableColumn<SystemProcess, Long>   processorColumn  = null;
    private TableColumn<SystemProcess, String> ownerColumn      = null;

    public ProcessTableView(SystemProcesses pProcessCollection)
    {
        oProcessCollection = pProcessCollection;
        oFilteredList      = new FilteredList<SystemProcess>( pProcessCollection.getCollection(), p -> true );
        oSortedList        = new SortedList<SystemProcess>( oFilteredList );

        pidColumn        = new TableColumn<SystemProcess, Long>("PID");
        nameColumn       = new TableColumn<SystemProcess, String>("Name");
        stateColumn      = new TableColumn<SystemProcess, String>("State");
        ppidColumn       = new TableColumn<SystemProcess, Long>("PPID");
        pgidColumn       = new TableColumn<SystemProcess, Long>("PGID");
        sessionColumn    = new TableColumn<SystemProcess, Long>("Session");
        tgidColumn       = new TableColumn<SystemProcess, Long>("TGID");
        minFaultsColumn  = new TableColumn<SystemProcess, Long>("Minor Faults");
        cMinFaultsColumn = new TableColumn<SystemProcess, Long>("Child Minor Faults");
        majFaultsColumn  = new TableColumn<SystemProcess, Long>("Major Faults");
        cMajFaultsColumn = new TableColumn<SystemProcess, Long>("Child Major Faults");
        utimeColumn      = new TableColumn<SystemProcess, Long>("User Time");
        stimeColumn      = new TableColumn<SystemProcess, Long>("System Time");
        cutimeColumn     = new TableColumn<SystemProcess, Long>("Child User Time");
        cstimeColumn     = new TableColumn<SystemProcess, Long>("Child System Time");
        priorityColumn   = new TableColumn<SystemProcess, Long>("Priority");
        niceColumn       = new TableColumn<SystemProcess, Long>("Nice Value");
        threadColumn     = new TableColumn<SystemProcess, Long>("Num Threads");
        startColumn      = new TableColumn<SystemProcess, Long>("Start Time");
        vsizeColumn      = new TableColumn<SystemProcess, Long>("Virtual Size");
        rssColumn        = new TableColumn<SystemProcess, Long>("Resident Set Size");
        processorColumn  = new TableColumn<SystemProcess, Long>("Processor Number");
        ownerColumn      = new TableColumn<SystemProcess, String>("Owner");

        pidColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("ProcessID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, String>("Name"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, String>("State"));
        ppidColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("ParentPID"));
        pgidColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("ProcessGroupID"));
        sessionColumn.setCellValueFactory(new PropertyValueFactory<SystemProcess, Long>("SessionID"));
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

        this.getColumns().add(pidColumn);
        this.getColumns().add(nameColumn);
        this.getColumns().add(stateColumn);
        this.getColumns().add(ppidColumn);
        this.getColumns().add(pgidColumn);
        this.getColumns().add(sessionColumn);
        this.getColumns().add(tgidColumn);
        this.getColumns().add(minFaultsColumn);
        this.getColumns().add(cMinFaultsColumn);
        this.getColumns().add(majFaultsColumn);
        this.getColumns().add(cMajFaultsColumn);
        this.getColumns().add(utimeColumn);
        this.getColumns().add(stimeColumn);
        this.getColumns().add(cutimeColumn);
        this.getColumns().add(cstimeColumn);
        this.getColumns().add(priorityColumn);
        this.getColumns().add(niceColumn);
        this.getColumns().add(threadColumn);
        this.getColumns().add(startColumn);
        this.getColumns().add(vsizeColumn);
        this.getColumns().add(rssColumn);
        this.getColumns().add(processorColumn);
        this.getColumns().add(ownerColumn);

        oSortedList.comparatorProperty().bind(this.comparatorProperty());
    }

    public TableColumn<SystemProcess, Long> getPIDColumn() { return pidColumn; }
    public TableColumn<SystemProcess, String> getNameColumn() { return nameColumn; }
    public TableColumn<SystemProcess, String> getStateColumn() { return stateColumn; }
    public TableColumn<SystemProcess, Long> getPPIDColumn() { return ppidColumn; }
    public TableColumn<SystemProcess, Long> getPGIDColumn() { return pgidColumn; }
    public TableColumn<SystemProcess, Long> getSessionColumn() { return sessionColumn; }
    public TableColumn<SystemProcess, Long> getTGIDColumn() { return tgidColumn; }
    public TableColumn<SystemProcess, Long> getMinFaultsColumn() { return minFaultsColumn; }
    public TableColumn<SystemProcess, Long> getChildMinFaultsColumn() { return cMinFaultsColumn; }
    public TableColumn<SystemProcess, Long> getMajFaultsColumn() { return majFaultsColumn; }
    public TableColumn<SystemProcess, Long> getChildMajFaultsColumn() { return cMajFaultsColumn; }
    public TableColumn<SystemProcess, Long> getUTimeColumn() { return utimeColumn; }
    public TableColumn<SystemProcess, Long> getSTimeColumn() { return stimeColumn; }
    public TableColumn<SystemProcess, Long> getChildUTimeColumn() { return cutimeColumn; }
    public TableColumn<SystemProcess, Long> getChildSTimeColumn() { return cstimeColumn; }
    public TableColumn<SystemProcess, Long> getPriorityColumn() { return priorityColumn; }
    public TableColumn<SystemProcess, Long> getNiceColumn() { return niceColumn; }
    public TableColumn<SystemProcess, Long> getThreadColumn() { return threadColumn; }
    public TableColumn<SystemProcess, Long> getStartColumn() { return startColumn; }
    public TableColumn<SystemProcess, Long> getVSizeColumn() { return vsizeColumn; }
    public TableColumn<SystemProcess, Long> getRSSColumn() { return rssColumn; }
    public TableColumn<SystemProcess, Long> getProcessorColumn() { return processorColumn; }
    public TableColumn<SystemProcess, String> getOwnerColumn() { return ownerColumn; }
}

