
package utilities;

/* Used as a container class in order to simplify accessing
   user specializatins. */

public class LecturerBenefits {
    private String field;
    private int boost;

    public LecturerBenefits(String field, int boost) {
        this.field = field;
        this.boost = boost;
    }

    public LecturerBenefits(String field) {
        this.field = field;
        boost = 0;
    }

    public String getField () {
        return field;
    }

    public int getBoost() {
        return boost;
    }

    public boolean equals(Object benefits) {
        return field.equals( ((LecturerBenefits)benefits).getField());
    }
}
