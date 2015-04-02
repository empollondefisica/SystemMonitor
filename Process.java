
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
    
    
    public Process( String fileText )
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
        
        String[] nameValues = fileText.split( "\n" );
        for( String nameValue : nameValues )
        {
            String[] tokens = nameValue.split( "\\s+" );
            if( tokens.length > 1 )
            {
            
                switch( tokens[0] )
                {
                case "Name:":    Name.setValue( tokens[1] ); break;
                case "State:":   State.setValue( tokens[1] ); break;
                case "Tgid:":    ThreadGroupID.setValue( Integer.parseInt( tokens[1] ) ); break;
                case "Pid:":     ProcessID.setValue( Integer.parseInt( tokens[1] ) ); break;
                case "PPid:":    ParentPID.setValue( Integer.parseInt( tokens[1] ) ); break;
                case "FDSize:":  FileDescriptors.setValue( Integer.parseInt( tokens[1] ) ); break;
                case "VmPeak:":  VmPeak.setValue( Integer.parseInt( tokens[1] ) ); break;
                case "VmSize:":  VmSize.setValue( Integer.parseInt( tokens[1] ) ); break;
                case "VmHWM:":   VmHighWaterMark.setValue( Integer.parseInt( tokens[1] ) ); break;
                case "VmRSS:":   VmResidentSetSize.setValue( Integer.parseInt( tokens[1] ) ); break;
                case "VmData:":  VmDataSize.setValue( Integer.parseInt( tokens[1] ) ); break;
                case "VmStk:":   VmStackSize.setValue( Integer.parseInt( tokens[1] ) ); break;
                case "VmExe:":   VmExecutableSize.setValue( Integer.parseInt( tokens[1] ) ); break;
                case "VmLib:":   VmLibrarySize.setValue( Integer.parseInt( tokens[1] ) ); break;
                case "VmPTE:":   VmPageTableEntries.setValue( Integer.parseInt( tokens[1] ) ); break;
                case "Threads:": Threads.setValue( Integer.parseInt( tokens[1] ) ); break;
                default: break;
                }
            }
        }
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
    
    public String toString()
    {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append( "Name: " + Name.getValue() + "\n" );
        strBuilder.append( "State: " + State.getValue() + "\n" );
        strBuilder.append( "Tgid: " + ThreadGroupID.getValue() + "\n" );
        strBuilder.append( "PID: " + ProcessID.getValue() + "\n" );
        strBuilder.append( "FDSize: " + FileDescriptors.getValue() + "\n" );
        strBuilder.append( "VmPeak: " + VmPeak.getValue() + "\n" );
        strBuilder.append( "VmSize: " + VmSize.getValue() + "\n" );
        strBuilder.append( "VmHWM: " + VmHighWaterMark.getValue() + "\n" );
        strBuilder.append( "VmRSS: " + VmResidentSetSize.getValue() + "\n" );
        strBuilder.append( "VmData: " + VmDataSize.getValue() + "\n" );
        strBuilder.append( "VmStk: " + VmStackSize.getValue() + "\n" );
        strBuilder.append( "VmExe: " + VmExecutableSize.getValue() + "\n" );
        strBuilder.append( "VmLib: " + VmLibrarySize.getValue() + "\n" );
        strBuilder.append( "VmPTE: " + VmPageTableEntries.getValue() + "\n" );
        strBuilder.append( "Threads: " + Threads.getValue() + "\n" );
        return strBuilder.toString();
    }
}
