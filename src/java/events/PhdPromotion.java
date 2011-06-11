package events;

public class PhdPromotion extends Event {

    @Override
    public void invoke() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getInfo() {
        return("Phd promotion");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.PHD_PROMOTION));
    }

}
