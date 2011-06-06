
package game.lecturerSystem;

public class LecturerBenefits {
    private String field;
    private int boost;

    public LecturerBenefits(String field, int boost) {
        this.field = field;
        this.boost = boost;
    }

    public String getField () {
        return field;
    }

    public int getBoost() {
        return boost;
    }
}
