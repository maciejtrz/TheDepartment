
package events;

public class FacebookBlocked extends Event {

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
        return name.equals(LotteryManager.FACEBOOK_BLOCKED);
    }

}
