package utilities;

public class BuildingInfo {

    /* Informs about the conditions met or not met when player
       tries to build a given building. */

    private boolean result;
    private String reason;

    public BuildingInfo(boolean result , String reason) {
        this.result = result;
        this.reason = reason;
    }

    public String getReason () {
        return reason;
    }

    public boolean getResult() {
        return result;
    }
}
