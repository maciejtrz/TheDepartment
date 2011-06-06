
package nameGenerator;

import java.util.ArrayList;
import java.util.Random;


public class NamesGenerator {

    private ArrayList<Nationality> nationalities;

    public NamesGenerator () {
        nationalities = new ArrayList<Nationality>();

        /* Creating all nationalities. */
        nationalities.add(new Polish());
        nationalities.add(new English());
        nationalities.add(new American());
        nationalities.add(new Italian());

    }


    public String generateName () {
        Nationality nationality = getRandomNat();
        String output = "Krzysztof Huszcza";

        /* Determining, whether it is a boy or girl. */
        Random rand = new Random ();
        int num = rand.nextInt(10);
        if (num > 6) {
            output = nationality.generateFamaleName();
        } else {
            output = nationality.generateMaleName();
        }
        return output;
    }

    private Nationality getRandomNat() {
        Random rand = new Random();
        int index = rand.nextInt(nationalities.size());
        return nationalities.get(index);
    }
}
