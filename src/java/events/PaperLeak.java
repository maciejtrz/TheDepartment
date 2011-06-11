package events;

public class PaperLeak extends Event {

    @Override
    public void invoke() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getInfo() {
        return("Examination paper leak");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.PAPER_LEAK));
    }


}
