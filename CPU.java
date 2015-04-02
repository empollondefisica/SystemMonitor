
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class CPU
{
    SimpleStringProperty  Name = null;
    SimpleIntegerProperty User = null;
    SimpleIntegerProperty Nice = null;
    SimpleIntegerProperty System = null;
    SimpleIntegerProperty Idle = null;
    SimpleIntegerProperty IOWait = null;
    SimpleIntegerProperty Interrupts = null;
    SimpleIntegerProperty SoftInterrupts = null;
    SimpleIntegerProperty Steal = null;
    
    public CPU( String fileText )
    {
        Name = new SimpleStringProperty();
        User = new SimpleIntegerProperty();
        Nice = new SimpleIntegerProperty();
        System = new SimpleIntegerProperty();
        Idle = new SimpleIntegerProperty();
        IOWait = new SimpleIntegerProperty();
        Interrupts = new SimpleIntegerProperty();
        SoftInterrupts = new SimpleIntegerProperty();
        Steal = new SimpleIntegerProperty();
        
        String[] tokens = fileText.split( "\\s+" );
        
        Name.setValue( tokens[0] );
        User.setValue( Integer.parseInt( tokens[1] ) );
        Nice.setValue( Integer.parseInt( tokens[2] ) );
        System.setValue( Integer.parseInt( tokens[3] ) );
        Idle.setValue( Integer.parseInt( tokens[4] ) );
        IOWait.setValue( Integer.parseInt( tokens[5] ) );
        Interrupts.setValue( Integer.parseInt( tokens[6] ) );
        SoftInterrupts.setValue( Integer.parseInt( tokens[7] ) );
        Steal.setValue( Integer.parseInt( tokens[8] ) );
    }
    
    public String getName() { return Name.getValue(); }

    public Integer getUser() { return User.getValue(); }
    
    public Integer getNice() { return Nice.getValue(); }
    
    public Integer getSystem() { return System.getValue(); }
    
    public Integer getIdle() { return Idle.getValue(); }
    
    public Integer getIOWait() { return IOWait.getValue(); }
    
    public Integer getInterrupts() { return Interrupts.getValue(); }
    
    public Integer getSoftInterrupts() { return SoftInterrupts.getValue(); }
    
    public Integer getSteal() { return Steal.getValue(); }
    
    public String toString()
    {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append( "Name: " + Name.getValue() + "\n" );
        strBuilder.append( "User: " + User.getValue() + "\n" );
        strBuilder.append( "Nice: " + Nice.getValue() + "\n" );
        strBuilder.append( "System: " + System.getValue() + "\n" );
        strBuilder.append( "Idle: " + Idle.getValue() + "\n" );
        strBuilder.append( "IOWait: " + IOWait.getValue() + "\n" );
        strBuilder.append( "Interrupts: " + Interrupts.getValue() + "\n" );
        strBuilder.append( "SoftInterrupts: " + SoftInterrupts.getValue() + "\n" );
        strBuilder.append( "Steal: " + Steal.getValue() + "\n" );
        return strBuilder.toString();
    }
}
