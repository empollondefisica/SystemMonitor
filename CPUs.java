
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class CPUs
{
    ObservableList<CPU> Collection = null;
    TableView<CPU> oTableView = null;
    
    public CPUs()
    {
        Collection = FXCollections.observableArrayList();
    }
    
    public void add( CPU cpu )
    {
        Collection.add( cpu );
    }
    
    public void add(
        String name,
        long user,
        long nice,
        long system,
        long idle,
        long iowait,
        long interrupts,
        long softinterrupts,
        long steal )
    {
        CPU cpu = new CPU();
        
        cpu.setName( name );
        cpu.setUser( user );
        cpu.setNice( nice );
        cpu.setSystem( system );
        cpu.setIdle( idle );
        cpu.setIOWait( iowait );
        cpu.setInterrupts( interrupts );
        cpu.setSoftInterrupts( softinterrupts );
        cpu.setSteal( steal );
        cpu.setModified( true );
        
        Collection.add( cpu );
    }
    
    public void add( String fileText )
    {
        String  name            = "";
        long     user            = 0;
        long     nice            = 0;
        long     system          = 0;
        long     idle            = 0;
        long     iowait          = 0;
        long     interrupts      = 0;
        long     softinterrupts  = 0;
        long     steal           = 0;
        
        String[] tokens = fileText.split( "\\s+" );
        
        name = tokens[0];
        user = Long.valueOf( tokens[1] );
        nice = Long.valueOf( tokens[2] );
        system = Long.valueOf( tokens[3] );
        idle = Long.valueOf( tokens[4] );
        iowait = Long.valueOf( tokens[5] );
        interrupts = Long.valueOf( tokens[6] );
        softinterrupts = Long.valueOf( tokens[7] );
        steal = Long.valueOf( tokens[8] );
        
        add( name, user, nice, system, idle, iowait, interrupts, softinterrupts, steal );
    }
    
    public void clear()
    {
        Collection.clear();
    }
    
    public int count()
    {
        return Collection.size();
    }
    
    public ObservableList<CPU> getCollection()
    {
        return Collection;
    }
    
    public void remove( CPU cpu )
    {
        Collection.remove( cpu );
    }
    
    public void remove( int index )
    {
        Collection.remove( index );
    }
    
    public int find( CPU cpu )
    {
        return Collection.indexOf( cpu );
    }
    
    public TableView<CPU> getTableView()
    {
        if(oTableView == null)
        {
            oTableView = new TableView<CPU>();
            
            TableColumn<CPU, String> nameColumn = new TableColumn<CPU, String>( "Name" );
            TableColumn<CPU, Long> userColumn = new TableColumn<CPU, Long>( "User" );
            TableColumn<CPU, Long> niceColumn = new TableColumn<CPU, Long>( "Nice" );
            TableColumn<CPU, Long> systemColumn = new TableColumn<CPU, Long>( "System" );
            TableColumn<CPU, Long> idleColumn = new TableColumn<CPU, Long>( "Idle" );
            TableColumn<CPU, Long> iowaitColumn = new TableColumn<CPU, Long>( "IOWait" );
            TableColumn<CPU, Long> interruptColumn = new TableColumn<CPU, Long>( "Interrupts" );
            TableColumn<CPU, Long> softInterruptColumn = new TableColumn<CPU, Long>( "SoftInterrupts" );
            TableColumn<CPU, Long> stealColumn = new TableColumn<CPU, Long>( "Steal" );
            
            nameColumn.setCellValueFactory( new PropertyValueFactory<CPU, String>( "Name" ) );
            userColumn.setCellValueFactory( new PropertyValueFactory<CPU, Long>( "User" ) );
            niceColumn.setCellValueFactory( new PropertyValueFactory<CPU, Long>( "Nice" ) );
            systemColumn.setCellValueFactory( new PropertyValueFactory<CPU, Long>( "System" ) );
            idleColumn.setCellValueFactory( new PropertyValueFactory<CPU, Long>( "Idle" ) );
            iowaitColumn.setCellValueFactory( new PropertyValueFactory<CPU, Long>( "IOWait" ) );
            interruptColumn.setCellValueFactory( new PropertyValueFactory<CPU, Long>( "Interrupts" ) );
            softInterruptColumn.setCellValueFactory( new PropertyValueFactory<CPU, Long>( "SoftInterrupts" ) );
            stealColumn.setCellValueFactory( new PropertyValueFactory<CPU, Long>( "Steal" ) );
            
            oTableView.getColumns().add( nameColumn );
            oTableView.getColumns().add( userColumn );
            oTableView.getColumns().add( niceColumn );
            oTableView.getColumns().add( systemColumn );
            oTableView.getColumns().add( idleColumn );
            oTableView.getColumns().add( iowaitColumn );
            oTableView.getColumns().add( interruptColumn );
            oTableView.getColumns().add( softInterruptColumn );
            oTableView.getColumns().add( stealColumn );
            
            oTableView.setItems( Collection );
        }
        
        return oTableView;
    }
}
