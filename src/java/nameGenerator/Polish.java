
package nameGenerator;

public class Polish implements Nationality {

    public String generateFamaleName() {
        return Utilities.generatePolishName(Utilities.POLISH_FEMALE_FIRST_NAMES,
                Utilities.POLISH_SURNAMES_PREFIXES ,
                Utilities.POLISH_SURNAMES_FEMALE_SUFFIXES);

    }

    public String generateMaleName() {
        return Utilities.generatePolishName(Utilities.POLISH_MALE_FIRST_NAMES,
                Utilities.POLISH_SURNAMES_PREFIXES ,
                Utilities.POLISH_SURNAMES_MALE_SUFFIXES);
    }

}
