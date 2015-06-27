
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleBooleanProperty;


public class CPU
{
    SimpleStringProperty  Name           = null;
    SimpleLongProperty    User           = null;
    SimpleLongProperty    Nice           = null;
    SimpleLongProperty    System         = null;
    SimpleLongProperty    Idle           = null;
    SimpleLongProperty    IOWait         = null;
    SimpleLongProperty    Interrupts     = null;
    SimpleLongProperty    SoftInterrupts = null;
    SimpleLongProperty    Steal          = null;
    SimpleBooleanProperty Modified       = null;

    public CPU()
    {
        Name            = new SimpleStringProperty();
        User            = new SimpleLongProperty();
        Nice            = new SimpleLongProperty();
        System          = new SimpleLongProperty();
        Idle            = new SimpleLongProperty();
        IOWait          = new SimpleLongProperty();
        Interrupts      = new SimpleLongProperty();
        SoftInterrupts  = new SimpleLongProperty();
        Steal           = new SimpleLongProperty();
        Modified        = new SimpleBooleanProperty();
    }

    public void update(CPU cpu)
    {
        Name.setValue(cpu.getName());
        User.setValue(cpu.getUser());
        Nice.setValue(cpu.getNice());
        System.setValue(cpu.getSystem());
        Idle.setValue(cpu.getIdle());
        IOWait.setValue(cpu.getIOWait());
        Interrupts.setValue(cpu.getInterrupts());
        SoftInterrupts.setValue(cpu.getSoftInterrupts());
        Steal.setValue(cpu.getSteal());
        Modified.setValue(true);
    }

    public void update(
        String name,
        long user,
        long nice,
        long system,
        long idle,
        long iowait,
        long interrupts,
        long softinterrupts,
        long steal)
    {
        Name.setValue(name);
        User.setValue(user);
        Nice.setValue(nice);
        System.setValue(system);
        Idle.setValue(idle);
        IOWait.setValue(iowait);
        Interrupts.setValue(interrupts);
        SoftInterrupts.setValue(softinterrupts);
        Steal.setValue(steal);
        Modified.setValue(true);
    }


    public String getName() { return Name.getValue(); }

    public Long getUser() { return User.getValue(); }

    public Long getNice() { return Nice.getValue(); }

    public Long getSystem() { return System.getValue(); }

    public Long getIdle() { return Idle.getValue(); }

    public Long getIOWait() { return IOWait.getValue(); }

    public Long getInterrupts() { return Interrupts.getValue(); }

    public Long getSoftInterrupts() { return SoftInterrupts.getValue(); }

    public Long getSteal() { return Steal.getValue(); }

    public boolean getModified() { return Modified.getValue(); }

    public Long getWork() { return getUser() + getSystem(); }

    public Long getTotal() { return getUser() + getSystem() + getNice() + getIdle() + getIOWait() + getInterrupts() + getSoftInterrupts() + getSteal(); }


    public void setName(String name) { Name.setValue(name); }

    public void setUser(long user) { User.setValue(user); }

    public void setNice(long nice) { Nice.setValue(nice); }

    public void setSystem(long system) { System.setValue(system); }

    public void setIdle(long idle) { Idle.setValue(idle); }

    public void setIOWait(long iowait) { IOWait.setValue(iowait); }

    public void setInterrupts(long interrupts) { Interrupts.setValue(interrupts); }

    public void setSoftInterrupts(long softinterrupts) { SoftInterrupts.setValue(softinterrupts); }

    public void setSteal(long steal) { Steal.setValue(steal); }

    public void setModified(boolean modified) { Modified.setValue(modified); }


    public boolean equals(Object object)
    {
        if(object == null)
        {
            return false;
        }
        else if(!(object instanceof CPU))
        {
            return false;
        }
        else
        {
            CPU other = (CPU)object;
            return this.getName().equalsIgnoreCase(other.getName());
        }
    }

    public String toString()
    {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(Name.getValue() + " ");
        strBuilder.append(User.getValue() + " ");
        strBuilder.append(Nice.getValue() + " ");
        strBuilder.append(System.getValue() + " ");
        strBuilder.append(Idle.getValue() + " ");
        strBuilder.append(IOWait.getValue() + " ");
        strBuilder.append(Interrupts.getValue() + " ");
        strBuilder.append(SoftInterrupts.getValue() + " ");
        strBuilder.append(Steal.getValue());

        return strBuilder.toString();
    }
}
