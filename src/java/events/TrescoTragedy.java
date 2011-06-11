package events;

public class TrescoTragedy extends Event {

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
        return (name.equals(LotteryManager.TRESCO_TRAGEDY));
    }
}
