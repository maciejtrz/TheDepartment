package events;

public class OutOfChicken extends Event {

    @Override
    public void invoke() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.OUT_OF_CHICKEN));
    }

}
