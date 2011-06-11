package events;

public class PintosCw extends Event {

    @Override
    public void invoke() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getInfo() {
        return "PintosCw";
    }


    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.PINTOS_CW));
    }
}
