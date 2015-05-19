
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Process
{    
    private SimpleStringProperty  Name               = null;
    private SimpleStringProperty  State              = null;
    private SimpleIntegerProperty ThreadGroupID      = null;
    private SimpleIntegerProperty ProcessID          = null;
    private SimpleIntegerProperty ParentPID          = null;
    private SimpleIntegerProperty FileDescriptors    = null;
    private SimpleIntegerProperty VmPeak             = null;
    private SimpleIntegerProperty VmSize             = null;
    private SimpleIntegerProperty VmHighWaterMark    = null;
    private SimpleIntegerProperty VmResidentSetSize  = null;
    private SimpleIntegerProperty VmDataSize         = null;
    private SimpleIntegerProperty VmStackSize        = null;
    private SimpleIntegerProperty VmExecutableSize   = null;
    private SimpleIntegerProperty VmLibrarySize      = null;
    private SimpleIntegerProperty VmPageTableEntries = null;
    private SimpleIntegerProperty Threads            = null;
    private SimpleBooleanProperty Modified           = null;
    
    
    public Process()
    {
        Name               = new SimpleStringProperty();
        State              = new SimpleStringProperty();
        ThreadGroupID      = new SimpleIntegerProperty();
        ProcessID          = new SimpleIntegerProperty();
        ParentPID          = new SimpleIntegerProperty();
        FileDescriptors    = new SimpleIntegerProperty();
        VmPeak             = new SimpleIntegerProperty();
        VmSize             = new SimpleIntegerProperty();
        VmHighWaterMark    = new SimpleIntegerProperty();
        VmResidentSetSize  = new SimpleIntegerProperty();
        VmDataSize         = new SimpleIntegerProperty();
        VmStackSize        = new SimpleIntegerProperty();
        VmExecutableSize   = new SimpleIntegerProperty();
        VmLibrarySize      = new SimpleIntegerProperty();
        VmPageTableEntries = new SimpleIntegerProperty();
        Threads            = new SimpleIntegerProperty();
        Modified           = new SimpleBooleanProperty();
    }
    
    public void update(
        String name,
        String state,
        int tgid,
        int pid,
        int ppid,
        int fdsize,
        int vmpeak,
        int vmsize,
        int vmhwm,
        int vmrss,
        int vmdata,
        int vmstk,
        int vmexe,
        int vmlib,
        int vmpte,
        int threads )
    {
        Name.setValue( name );
        State.setValue( state );
        ThreadGroupID.setValue( tgid );
        ProcessID.setValue( pid );
        ParentPID.setValue( ppid );
        FileDescriptors.setValue( fdsize );
        VmPeak.setValue( vmpeak );
        VmSize.setValue( vmsize );
        VmHighWaterMark.setValue( vmhwm );
        VmResidentSetSize.setValue( vmrss );
        VmDataSize.setValue( vmdata );
        VmStackSize.setValue( vmstk );
        VmExecutableSize.setValue( vmexe );
        VmLibrarySize.setValue( vmlib );
        VmPageTableEntries.setValue( vmpte );
        Threads.setValue( threads );
        Modified.setValue( true );
    }
    
    public String getName() { return Name.getValue(); }
    
    public String getState() { return State.getValue(); }
    
    public int getThreadGroupID() { return ThreadGroupID.getValue(); }
    
    public int getProcessID() { return ProcessID.getValue(); }
    
    public int getParentPID() { return ParentPID.getValue();  }
    
    public int getFileDescriptors() { return FileDescriptors.getValue(); }
    
    public int getVmPeak() { return VmPeak.getValue(); }
    
    public int getVmSize() { return VmSize.getValue(); }
    
    public int getVmHighWaterMark() { return VmHighWaterMark.getValue(); }
    
    public int getVmResidentSetSize() { return VmResidentSetSize.getValue(); }
    
    public int getVmDataSize() { return VmDataSize.getValue(); }
    
    public int getVmStackSize() { return VmStackSize.getValue(); }
    
    public int getVmExecutableSize() { return VmExecutableSize.getValue(); }
    
    public int getVmLibrarySize() { return VmLibrarySize.getValue(); }
    
    public int getVmPageTableEntries() { return VmPageTableEntries.getValue(); }
    
    public int getThreads() { return Threads.getValue(); }
    
    public boolean getModified() { return Modified.getValue(); }
    
    
    public void setName( String name ) { Name.setValue( name ); }
    
    public void setState( String state ) { State.setValue( state ); }
    
    public void setThreadGroupID( int tgid ) { ThreadGroupID.setValue( tgid ); }
    
    public void setProcessID( int pid ) { ProcessID.setValue( pid ); }
    
    public void setParentPID( int ppid ) { ParentPID.setValue( ppid );  }
    
    public void setFileDescriptors( int fdsize ) { FileDescriptors.setValue( fdsize ); }
    
    public void setVmPeak( int vmpeak ) { VmPeak.setValue( vmpeak ); }
    
    public void setVmSize( int vmsize ) { VmSize.setValue( vmsize ); }
    
    public void setVmHighWaterMark( int vmhwm ) { VmHighWaterMark.setValue( vmhwm ); }
    
    public void setVmResidentSetSize( int vmrss ) { VmResidentSetSize.setValue( vmrss ); }
    
    public void setVmDataSize( int vmdata ) { VmDataSize.setValue( vmdata ); }
    
    public void setVmStackSize( int vmstack ) { VmStackSize.setValue( vmstack ); }
    
    public void setVmExecutableSize( int vmexe ) { VmExecutableSize.setValue( vmexe ); }
    
    public void setVmLibrarySize( int vmlib ) { VmLibrarySize.setValue( vmlib ); }
    
    public void setVmPageTableEntries( int vmpte ) { VmPageTableEntries.setValue( vmpte ); }
    
    public void setThreads( int threads ) { Threads.setValue( threads ); }
    
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
        strBuilder.append( Name.getValue() + " " );
        strBuilder.append( State.getValue() + " " );
        strBuilder.append( ThreadGroupID.getValue() + " " );
        strBuilder.append( ProcessID.getValue() + " " );
        strBuilder.append( FileDescriptors.getValue() + " " );
        strBuilder.append( VmPeak.getValue() + " " );
        strBuilder.append( VmSize.getValue() + " " );
        strBuilder.append( VmHighWaterMark.getValue() + " " );
        strBuilder.append( VmResidentSetSize.getValue() + " " );
        strBuilder.append( VmDataSize.getValue() + " " );
        strBuilder.append( VmStackSize.getValue() + " " );
        strBuilder.append( VmExecutableSize.getValue() + " " );
        strBuilder.append( VmLibrarySize.getValue() + " " );
        strBuilder.append( VmPageTableEntries.getValue() + " " );
        strBuilder.append( Threads.getValue() );
        return strBuilder.toString();
    }
}
