package events;

public class BarNight extends Event {

    @Override
    public void invoke() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getInfo() {
        return "BarNight";
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.BAR_NIGHT));
    }

}
