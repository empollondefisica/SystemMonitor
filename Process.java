
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Process
{
    private SimpleLongProperty ProcessID          = null;
    private SimpleStringProperty  Name               = null;
    private SimpleStringProperty  State              = null;
    private SimpleLongProperty ParentPID          = null;
    private SimpleLongProperty ProcessGroupID     = null;
    private SimpleLongProperty SessionID          = null;
    private SimpleLongProperty ThreadGroupID      = null;
    private SimpleLongProperty MinorFaults        = null;
    private SimpleLongProperty ChildMinorFaults   = null;
    private SimpleLongProperty MajorFaults        = null;
    private SimpleLongProperty ChildMajorFaults   = null;
    private SimpleLongProperty UserTime           = null;
    private SimpleLongProperty SystemTime         = null;
    private SimpleLongProperty ChildUserTime      = null;
    private SimpleLongProperty ChildSystemTime    = null;
    private SimpleLongProperty Priority           = null;
    private SimpleLongProperty NiceValue          = null;
    private SimpleLongProperty NumThreads         = null;
    private SimpleLongProperty StartTime          = null;
    private SimpleLongProperty VirtualSize        = null;
    private SimpleLongProperty ResidentSetSize    = null;
    private SimpleLongProperty ProcessorNumber    = null;
    private SimpleBooleanProperty Modified           = null;
    
    public Process()
    {
        ProcessID          = new SimpleLongProperty();
        Name               = new SimpleStringProperty();
        State              = new SimpleStringProperty();
        ParentPID          = new SimpleLongProperty();
        ProcessGroupID     = new SimpleLongProperty();
        SessionID          = new SimpleLongProperty();
        ThreadGroupID      = new SimpleLongProperty();
        MinorFaults        = new SimpleLongProperty();
        ChildMinorFaults   = new SimpleLongProperty();
        MajorFaults        = new SimpleLongProperty();
        ChildMajorFaults   = new SimpleLongProperty();
        UserTime           = new SimpleLongProperty();
        SystemTime         = new SimpleLongProperty();
        ChildUserTime      = new SimpleLongProperty();
        ChildSystemTime    = new SimpleLongProperty();
        Priority           = new SimpleLongProperty();
        NiceValue          = new SimpleLongProperty();
        NumThreads         = new SimpleLongProperty();
        StartTime          = new SimpleLongProperty();
        VirtualSize        = new SimpleLongProperty();
        ResidentSetSize    = new SimpleLongProperty();
        ProcessorNumber    = new SimpleLongProperty();
        Modified           = new SimpleBooleanProperty();
    }
    
    public void update(
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
        ProcessID.setValue(processID);
        Name.setValue(name);
        State.setValue(state);
        ParentPID.setValue(ppid);
        ProcessGroupID.setValue(pgid);
        SessionID.setValue(sessionID);
        ThreadGroupID.setValue(tgid);
        MinorFaults.setValue(minFaults);
        ChildMinorFaults.setValue(cMinFaults);
        MajorFaults.setValue(majFaults);
        ChildMajorFaults.setValue(cMajFaults);
        UserTime.setValue(utime);
        SystemTime.setValue(stime);
        ChildUserTime.setValue(cutime);
        ChildSystemTime.setValue(cstime);
        Priority.setValue(priority);
        NiceValue.setValue(nice);
        NumThreads.setValue(threads);
        StartTime.setValue(start);
        VirtualSize.setValue(vsize);
        ResidentSetSize.setValue(rss);
        ProcessorNumber.setValue(processor);
        Modified.setValue( true );
    }
    
    public long getProcessID() { return ProcessID.getValue(); }
    
    public String getName() { return Name.getValue(); }
    
    public String getState() { return State.getValue(); }
    
    public long getParentPID() { return ParentPID.getValue(); }
    
    public long getProcessGroupID() { return ProcessGroupID.getValue(); }
    
    public long getSessionID() { return SessionID.getValue(); }
    
    public long getThreadGroupID() { return ThreadGroupID.getValue(); }
    
    public long getMinorFaults() { return MinorFaults.getValue(); }
    
    public long getChildMinorFaults() { return ChildMinorFaults.getValue(); }
    
    public long getMajorFaults() { return MajorFaults.getValue(); }
    
    public long getChildMajorFaults() { return ChildMajorFaults.getValue(); }
    
    public long getUserTime() { return UserTime.getValue(); }
    
    public long getSystemTime() { return SystemTime.getValue(); }
    
    public long getChildUserTime() { return ChildUserTime.getValue(); }
    
    public long getChildSystemTime() { return ChildSystemTime.getValue(); }
    
    public long getPriority() { return Priority.getValue(); }
    
    public long getNiceValue() { return NiceValue.getValue(); }
    
    public long getNumThreads() { return NumThreads.getValue(); }
    
    public long getStartTime() { return StartTime.getValue(); }
    
    public long getVirtualSize() { return VirtualSize.getValue(); }
    
    public long getResidentSetSize() { return ResidentSetSize.getValue(); }
    
    public long getProcessorNumber() { return ProcessorNumber.getValue(); }
    
    public boolean getModified() { return Modified.getValue(); }
    
    
    public void setProcessID(long pid) { ProcessID.setValue(pid); }
    
    public void setName(String name) { Name.setValue(name); }
    
    public void setState(String state) { State.setValue(state); }
    
    public void setParentPID(long ppid) { ParentPID.setValue(ppid); }
    
    public void setProcessGroupID(long pgid) { ProcessGroupID.setValue(pgid); }
    
    public void setSessionID(long sessionID) { SessionID.setValue(sessionID); }
    
    public void setThreadGroupID(long tgid) { ThreadGroupID.setValue(tgid); }
    
    public void setMinorFaults(long minFaults) { MinorFaults.setValue(minFaults); }
    
    public void setChildMinorFaults(long cMinFaults) { ChildMinorFaults.setValue(cMinFaults); }
    
    public void setMajorFaults(long majFaults) { MajorFaults.setValue(majFaults); }
    
    public void setChildMajorFaults(long cMajFaults) { ChildMajorFaults.setValue(cMajFaults); }
    
    public void setUserTime(long utime) { UserTime.setValue(utime); }
    
    public void setSystemTime(long stime) { SystemTime.setValue(stime); }
    
    public void setChildUserTime(long cutime) { ChildUserTime.setValue(cutime); }
    
    public void setChildSystemTime(long cstime) { ChildSystemTime.setValue(cstime); }
    
    public void setPriority(long priority) { Priority.setValue(priority); }
    
    public void setNiceValue(long nice) { NiceValue.setValue(nice); }
    
    public void setNumThreads(long threads) { NumThreads.setValue(threads); }
    
    public void setStartTime(long start) { StartTime.setValue(start); }
    
    public void setVirtualSize(long vsize) { VirtualSize.setValue(vsize); }
    
    public void setResidentSetSize(long rss) { ResidentSetSize.setValue(rss); }
    
    public void setProcessorNumber(long processor) { ProcessorNumber.setValue(processor); }
    
    public void setModified( boolean modified ) { Modified.setValue( modified ); }
    
    
    public boolean equals( Object object )
    {
        if( object == null )
        {
            return false;
        }
        else if( !( object instanceof Process ) )
        {
            return false;
        }
        else
        {
            Process other = (Process)object;
            return this.getProcessID() == other.getProcessID();
        }
    }
    
    public String toString()
    {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append( ProcessID.getValue() + " " );
        strBuilder.append( Name.getValue() + " " );
        strBuilder.append( State.getValue() + " " );
        strBuilder.append( ParentPID.getValue() + " " );
        strBuilder.append( ProcessGroupID.getValue() + " " );
        strBuilder.append( SessionID.getValue() + " " );
        strBuilder.append( ThreadGroupID.getValue() + " " );
        strBuilder.append( MinorFaults.getValue() + " " );
        strBuilder.append( ChildMinorFaults.getValue() + " " );
        strBuilder.append( MajorFaults.getValue() + " " );
        strBuilder.append( ChildMajorFaults.getValue() + " " );
        strBuilder.append( UserTime.getValue() + " " );
        strBuilder.append( SystemTime.getValue() + " " );
        strBuilder.append( ChildUserTime.getValue() + " " );
        strBuilder.append( ChildSystemTime.getValue() + " " );
        strBuilder.append( Priority.getValue() + " " );
        strBuilder.append( NiceValue.getValue() + " " );
        strBuilder.append( NumThreads.getValue() + " " );
        strBuilder.append( StartTime.getValue()  + " " );
        strBuilder.append( VirtualSize.getValue()  + " " );
        strBuilder.append( ResidentSetSize.getValue() + " " );
        strBuilder.append( ProcessorNumber.getValue() + " " );
        strBuilder.append( Modified.getValue() );
        
        return strBuilder.toString();
    }
}
