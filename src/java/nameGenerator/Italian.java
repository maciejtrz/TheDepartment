package nameGenerator;

public class Italian implements Nationality {

    public String generateFamaleName() {
                return Utilities.generateName
                (Utilities.ITALIAN_FEMALE_FIRST_NAMES,
                 Utilities.ITALIAN_SURNAMES);
    }

    public String generateMaleName() {
                return Utilities.generateName
                (Utilities.ITALIAN_MALE_FIRST_NAMES,
                 Utilities.ITALIAN_SURNAMES);
    }

}
