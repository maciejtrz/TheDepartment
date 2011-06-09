package nameGenerator;

public class Spanish implements Nationality {

    public String generateFamaleName() {
                return Utilities.generateName
                (Utilities.SPANISH_FEMALE_FIRST_NAMES,
                 Utilities.SPANISH_SURNAMES);
    }

    public String generateMaleName() {
                return Utilities.generateName
                (Utilities.SPANISH_MALE_FIRST_NAMES,
                 Utilities.SPANISH_SURNAMES);
    }

}
