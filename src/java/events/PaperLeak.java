package events;

import ConnectionDataBase.EventsHelper;

public class PaperLeak extends Event {

    @Override
    public boolean invoke(String playerName) {

        // No prerequirements.

        // Increasing students satisfaction
        increaseSatisfaction(playerName, LotteryManager.HIGH);

        return true;
    }

    @Override
    public String getInfo() {
        return("The examination papers leak occurred and the lecturers are " +
                "unaware of the fact. The satisfaction of your students " +
                "boosts up rapidly.  ");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.PAPER_LEAK));
    }


    @Override
    public String getName() {
        return ("Examination paper leak!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setPaperLeak(playerName, getNumOfTickets());
    }
}
