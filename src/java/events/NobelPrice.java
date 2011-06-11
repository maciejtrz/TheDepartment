package events;

public class NobelPrice extends Event {

    @Override
    public void invoke() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getInfo() {
        return ("Nobel price");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.NOBEL_PRICE));
    }

}
