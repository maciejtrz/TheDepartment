package events;

public class LabHacking extends Event {

    @Override
    public void invoke() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getInfo() {
        return ("Labs hacked!");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.LAB_HACKING));
    }
    
}
