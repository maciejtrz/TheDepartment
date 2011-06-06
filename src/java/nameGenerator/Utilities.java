package nameGenerator;

import java.util.ArrayList;
import java.util.Random;

public class Utilities {

    public static final String[] ENGLISH_MALE_FIRST_NAMES
            = {"Jack" , "Harry" , "Olivier" , "Thomas", "Marek" , "Paul" ,
               "Peter" , "William" , "James" , "Jack" , "Daniel" , "Jack" ,
               "Michael" , "Matthew" , "Nathan" , "George" , "Ryan" , "Harry",
               "Peter" , "Lucas" , "Alfie" , "Joshua" , "Karol" , "Chris" ,
               "Lars" , "Martin" , "Narankaranaranr" , "Bruce"};

    public static final String[] POLISH_MALE_FIRST_NAMES
            = {"Pawel" , "Piotrek" , "Marcin" , "Marek" , "Igor" , "Karol" ,
               "Krzysiek" , "Maciej" , "Donald" , "Radoslaw" , "Rafal" ,
               "Tomek" , "Jerzy" , "Antoni" , "Bronislaw" , "Wieslaw" ,
               "Adam"};

    public static final String[] SPANISH_MALE_FIRST_NAMES
            = {"Diego" , "Daniel" , "Pavlo" , "Pablo" , "Aljeandro" , "Alvaro" ,
               "Adrian" , "Javier" , "David" , "Serigo" , "Hugo" , "Fernando" ,
               "Alphonso" , "Amando" , "Drago" , "Heliberto" , "Gustavo" ,
               "Julio" , "Leonardo" , "Martin" , "Raul" , "Tito" , "Adolfo"};

    public static final String[] ITALIAN_MALE_FIRST_NAMES
            = {"Giuseppe" , "Antonio" , "Giovanni" , "Luigi" ,
               "Mario" , "Salvatore" , "Francesco" , "Vincenzo" , "Angelo" ,
               "Paolo" , "Roberto" , "Pietro" , "Franco" , "Domenico" , "Carlo",
               "Michele" , "Bruno" , "Giorgio" , "Andrea"};

    public static final String[] ITALIAN_FEMALE_FIRST_NAMES
            = {"Maria" , "Anna" , "Rosa" , "Angela" , "Teresa" ,
               "Giuseppina" , "Lucia" , "Francesa" , "Paola" , "Carmela" , "Rita",
               "Luisa" , "Grazia" , "Laura" , "Carla" , "Casterina" , "Giuseppa" , 
               "Elena"};

    public static final String[] SPANISH_FEMALE_FIRST_NAMES
            = {"Aljeandra" , "Alondra" , "Ana" , "Belinda" , "Brisa" , "Pamela" ,
               "Lola" , "Linda" , "Malia" , "Mercedes" , "Mia" , "Paloma"};

    public static final String[] POLISH_FEMALE_FIRST_NAMES
            = {"Anna" , "Katarzyna" , "Hanna" , "Maria" , "Aneta" , "Julia" ,
               "Borzena" , "Elzbieta" , "Krystyna" , "Wioletta" , "Natalia" ,
               "Magda" , "Marta" , "Majka" , "Martyna" , "Dominika"};

    public static final String[] ENGLISH_FEMALE_FIRST_NAMES
            = {"Maya" , "Alexandra" , "Pamela" , "Sussan", "Ada" , "Olivia" ,
              "Sophie" , "Anna" , "Emily" , "Chloe" , "Jessica" , "Isabella" ,
              "Amelia" , "Sophia" , "Ruby" , "Grace"};

    public static final String[] ENGLISH_SURNAMES
         = {"Cook" , "Brown" , "Smith" , "Field" , "Jones" , "Miller",
        "Davis" , "Garcia" , "Taylor" , "Moore" , "Jackson" , "While" ,
        "Lopez" , "Harris" , "Clark" , "Young" , "Kind" , "Scott" , "Walker" ,
        "Allen" , "King" , "Wright" , "Campbell" , "Phillips" , "Roberts" , "Reed" ,
        "Kelly" , "Howard" , "Cox" , "Wood" , "Richardson" , "Price" , "Fisher"};

    public static final String[] POLISH_SURNAMES_PREFIXES
            = {"Malin" , "Kowal" , "Wozni" , "Woj" , "Kraw" , "Lewandow" ,
               "Piotrow" , "Marian" , "Kozlow" , "Wojciechow" , "Zielin" ,
               "Bagin" , "Dabrow" , "Kwiatkow" , "Jankow"};

    public static final String[] POLISH_SURNAMES_MALE_SUFFIXES
            = {"ski" , "czyk" , "cik" , "czak" , "ek" , "ak"};

    public static final String[] POLISH_SURNAMES_FEMALE_SUFFIXES
            = {"ska" , "czyk" , "cik" , "czak" , "ek" , "ak" };

    public static final String[] ITALIAN_SURNAMES
            = {"Rossi" , "Russo" , "Ferrari" , "Bianchi" , "Colombo" , "Ricci" ,
               "Marino" , "Gallo" , "Conti" , "Giordano" , "Rizzo"  , "Lombardi" ,
               "Mancini" , "Moretti" , "Bruno" , "Marino" , "De Luca" , "Romano" ,
               "Esposito" , "Greco" , "Costa"};

    public static final String[] SPANISH_SURNAMES
            = {"Garcia" , "Fernandez" , "Hernandez" , "Sanchez" , "Marin" ,
               "Lopez" , "Gonzalez" , "Torres" , "Moreno" , "Ruiz" , "Alvarez" ,
               "Diaz" , "Gil" , "Serrano" , "Ruiz" , "Munoz" , "Jimenez" , "Serrano" ,
               "Suarez" , "Castro" , "Ramirez" , "Morales" , "Iglesias" , "Blanco"};

    public static String generateName(String[] firstName, String[] surName) {

        Random rand = new Random ();
        int name_index = rand.nextInt(firstName.length);
        int surname_index = rand.nextInt(surName.length);

        return (firstName[name_index] + " " + surName[surname_index]);
    }

    public static String generatePolishName(String[] firstName, String[] prefix,
            String[] suffix) {
        Random rand =  new Random ();
        int name_index = rand.nextInt(firstName.length);
        int prefix_index = rand.nextInt(prefix.length);
        int suffix_index = rand.nextInt(suffix.length);

        return (firstName[name_index] + " " + prefix[prefix_index] +
                suffix[suffix_index]);
    }
}
