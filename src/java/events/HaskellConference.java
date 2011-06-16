package events;

import ConnectionDataBase.EventsHelper;
import utilities.LecturersManager;

public class HaskellConference extends Event {

    @Override
    public boolean invoke(String userName) {

        // No prerequirements.

        // Updating the database, adding one boost value to each lecturer.
        LecturersManager mgr = new LecturersManager (userName);
        return mgr.updateLecturersBoost(1);
    }

    @Override
    public String getInfo() {
        return "You should be proud of your department! It was appointed as " +
                "the next organizer of the Haskell Global Conference. " +
                "All of your lecturers, even those unrelated to functional " +
                "programming, became very excited about the event. Sadly, " +
                "your undergrads and Ph.D. do not care. The title of this year's " +
                "conference is \"Zygohistomorphic prepromorphisms and its practial" +
                " uses in Hello Haskell applications\". ";
    }

    @Override
    public boolean isEqual(String name) {
        return name.equals(LotteryManager.HASKELL_CONFERENCE);
    }

    @Override
    public String getName() {
        return ("Haskell Conference!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setHaskellConference(playerName, getNumOfTickets());
    }
}
