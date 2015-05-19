
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Processes
{
    ObservableList<Process> Collection = null;
    
    public Processes()
    {
        Collection = FXCollections.observableArrayList();
    }
    
    public void add( Process process )
    {
        Collection.add( process );
    }
    
    public void add(
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
        Process process = new Process();
        
        process.setName( name );
        process.setState( state );
        process.setThreadGroupID( tgid );
        process.setProcessID( pid );
        process.setParentPID( ppid );
        process.setFileDescriptors( fdsize );
        process.setVmPeak( vmpeak );
        process.setVmSize( vmsize );
        process.setVmHighWaterMark( vmhwm );
        process.setVmHighWaterMark( vmrss );
        process.setVmDataSize( vmdata );
        process.setVmStackSize( vmstk );
        process.setVmExecutableSize( vmexe );
        process.setVmLibrarySize( vmlib );
        process.setVmPageTableEntries( vmpte );
        process.setThreads( threads );
        process.setModified( true );
        
        Collection.add( process );
    }
    
    public void add( String fileText )
    {
        String  name    = "";
        String  state   = "";
        int     tgid    = 0;
        int     pid     = 0;
        int     ppid    = 0;
        int     fdsize  = 0;
        int     vmpeak  = 0;
        int     vmsize  = 0;
        int     vmhwm   = 0;
        int     vmrss   = 0;
        int     vmdata  = 0;
        int     vmstk   = 0;
        int     vmexe   = 0;
        int     vmlib   = 0;
        int     vmpte   = 0;
        int     threads = 0;
        
        String[] nameValues = fileText.split( "\n" );
        for( String nameValue : nameValues )
        {
            
            String[] tokens = nameValue.split( "\\s+" );
            if( tokens.length > 1 )
            {
                switch( tokens[0] )
                {
                case "Name:":    name = tokens[1]; break;
                case "State:":   state = tokens[1]; break;
                case "Tgid:":    tgid = Integer.parseInt( tokens[1] ); break;
                case "Pid:":     pid = Integer.parseInt( tokens[1] ); break;
                case "PPid:":    ppid = Integer.parseInt( tokens[1] ); break;
                case "FDSize:":  fdsize = Integer.parseInt( tokens[1] ); break;
                case "VmPeak:":  vmpeak = Integer.parseInt( tokens[1] ); break;
                case "VmSize:":  vmsize = Integer.parseInt( tokens[1] ); break;
                case "VmHWM:":   vmhwm = Integer.parseInt( tokens[1] ); break;
                case "VmRSS:":   vmrss = Integer.parseInt( tokens[1] ); break;
                case "VmData:":  vmdata = Integer.parseInt( tokens[1] ); break;
                case "VmStk:":   vmstk = Integer.parseInt( tokens[1] ); break;
                case "VmExe:":   vmexe = Integer.parseInt( tokens[1] ); break;
                case "VmLib:":   vmlib = Integer.parseInt( tokens[1] ); break;
                case "VmPTE:":   vmpte = Integer.parseInt( tokens[1] ); break;
                case "Threads:": threads = Integer.parseInt( tokens[1] ); break;
                default: break;
                }
            }
        }

        add( name, state, tgid, pid, ppid, fdsize, vmpeak, vmsize, vmhwm, vmrss, vmdata, vmstk, vmexe, vmlib, vmpte, threads );
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
}
