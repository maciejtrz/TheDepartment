
package nameGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class NamesGenerator {

    private static ArrayList<Nationality> nationalities;
    private static Set<String> generatedNames;
    private static NamesGenerator generator = null;

    private NamesGenerator () {
        nationalities = new ArrayList<Nationality>();
        generatedNames = new HashSet<String>();

        /* Creating all nationalities. */
        nationalities.add(new Polish());
        nationalities.add(new English());
        nationalities.add(new American());
        nationalities.add(new Italian());

    }

    public static NamesGenerator createNamesGenerator() {
        if (generator == null) {
            generator = new NamesGenerator();
        }
        return generator;
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
        num = rand.nextInt(1000);
        if (num > 997) {
                // With 0.2% generate a prefix
                String prefix = generatePrefix();
                output = prefix + " " + output;
            }
        if (generatedNames.contains(output)) {
            // The names was already generated
            String secondName = generateName();

            if (secondName.equals(output)) {
                // We are really unlucky.
                String randomized = randomize();
                output = output + "-" + randomized;
                while (generatedNames.contains(output)) {
                    randomized = randomize();
                    output = output + "-" + randomized;
                }
            }
            else {
                output = output + "-" + secondName;
            }
        }
        return output;
    }

    private static Nationality getRandomNat() {
        Random rand = new Random();
        int index = rand.nextInt(nationalities.size());
        return nationalities.get(index);
    }

    private static String generatePrefix() {
        Random rand = new Random();
        int index = rand.nextInt(Utilities.RANDOM_SEPERATORS.length);
        return Utilities.RANDOM_SEPERATORS[index];
    }

    private static String randomize() {
        String output = "";
        String temp = "aaabcdeeeefghiijklmnooprstuwz";
        for (int i = 0; i < temp.length() ; i++) {
            Random rand = new Random();
            int index = rand.nextInt(temp.length());
            output += temp.charAt(index);
        }
        return output;
    }
}
