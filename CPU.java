
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleBooleanProperty;


public class CPU
{
    SimpleStringProperty  oName           = null;
    SimpleLongProperty    oUser           = null;
    SimpleLongProperty    oNice           = null;
    SimpleLongProperty    oSystem         = null;
    SimpleLongProperty    oIdle           = null;
    SimpleLongProperty    oIOWait         = null;
    SimpleLongProperty    oInterrupts     = null;
    SimpleLongProperty    oSoftInterrupts = null;
    SimpleLongProperty    oSteal          = null;
    SimpleBooleanProperty oModified       = null;

    public CPU()
    {
        oName            = new SimpleStringProperty();
        oUser            = new SimpleLongProperty();
        oNice            = new SimpleLongProperty();
        oSystem          = new SimpleLongProperty();
        oIdle            = new SimpleLongProperty();
        oIOWait          = new SimpleLongProperty();
        oInterrupts      = new SimpleLongProperty();
        oSoftInterrupts  = new SimpleLongProperty();
        oSteal           = new SimpleLongProperty();
        oModified        = new SimpleBooleanProperty();
    }

    public void update(CPU cpu)
    {
        oName.setValue(cpu.getName());
        oUser.setValue(cpu.getUser());
        oNice.setValue(cpu.getNice());
        oSystem.setValue(cpu.getSystem());
        oIdle.setValue(cpu.getIdle());
        oIOWait.setValue(cpu.getIOWait());
        oInterrupts.setValue(cpu.getInterrupts());
        oSoftInterrupts.setValue(cpu.getSoftInterrupts());
        oSteal.setValue(cpu.getSteal());
        oModified.setValue(true);
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
        oName.setValue(name);
        oUser.setValue(user);
        oNice.setValue(nice);
        oSystem.setValue(system);
        oIdle.setValue(idle);
        oIOWait.setValue(iowait);
        oInterrupts.setValue(interrupts);
        oSoftInterrupts.setValue(softinterrupts);
        oSteal.setValue(steal);
        oModified.setValue(true);
    }


    public String getName() { return oName.getValue(); }

    public Long getUser() { return oUser.getValue(); }

    public Long getNice() { return oNice.getValue(); }

    public Long getSystem() { return oSystem.getValue(); }

    public Long getIdle() { return oIdle.getValue(); }

    public Long getIOWait() { return oIOWait.getValue(); }

    public Long getInterrupts() { return oInterrupts.getValue(); }

    public Long getSoftInterrupts() { return oSoftInterrupts.getValue(); }

    public Long getSteal() { return oSteal.getValue(); }

    public boolean getModified() { return oModified.getValue(); }

    public Long getWork() { return getUser() + getSystem(); }

    public Long getTotal() { return getUser() + getSystem() + getNice() + getIdle() + getIOWait() + getInterrupts() + getSoftInterrupts() + getSteal(); }


    public void setName(String name) { oName.setValue(name); }

    public void setUser(long user) { oUser.setValue(user); }

    public void setNice(long nice) { oNice.setValue(nice); }

    public void setSystem(long system) { oSystem.setValue(system); }

    public void setIdle(long idle) { oIdle.setValue(idle); }

    public void setIOWait(long iowait) { oIOWait.setValue(iowait); }

    public void setInterrupts(long interrupts) { oInterrupts.setValue(interrupts); }

    public void setSoftInterrupts(long softinterrupts) { oSoftInterrupts.setValue(softinterrupts); }

    public void setSteal(long steal) { oSteal.setValue(steal); }

    public void setModified(boolean modified) { oModified.setValue(modified); }


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

    public int hashCode()
    {
        return oName.hashCode();
    }

    public String toString()
    {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(oName.getValue() + " ");
        strBuilder.append(oUser.getValue() + " ");
        strBuilder.append(oNice.getValue() + " ");
        strBuilder.append(oSystem.getValue() + " ");
        strBuilder.append(oIdle.getValue() + " ");
        strBuilder.append(oIOWait.getValue() + " ");
        strBuilder.append(oInterrupts.getValue() + " ");
        strBuilder.append(oSoftInterrupts.getValue() + " ");
        strBuilder.append(oSteal.getValue());

        return strBuilder.toString();
    }
}
