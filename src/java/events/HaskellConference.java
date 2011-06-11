package events;

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEqual(String name) {
        return name.equals(LotteryManager.HASKELL_CONFERENCE);
    }

}
