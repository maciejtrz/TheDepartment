
package nameGenerator;

public class English implements Nationality {

    public String generateFamaleName() {
                return Utilities.generateName
                (Utilities.ENGLISH_FEMALE_FIRST_NAMES,
                 Utilities.ENGLISH_SURNAMES);
    }

    public String generateMaleName() {
                return Utilities.generateName
                (Utilities.ENGLISH_MALE_FIRST_NAMES,
                 Utilities.ENGLISH_SURNAMES);
    }

}
