package specializationsGenerator;

import java.util.Random;


public class SpecializationsGenerator {

    public static String[] subjectList = {"AI", "Machine Learning",
        "Compilers", "Operating System Design", "Networks and Communication",
        "Models of Computation"
    };

    private static SpecializationsGenerator generator = null;

    private SpecializationsGenerator() {

    }

    public static SpecializationsGenerator createSpecializationsGenerator() {
        if (generator == null) {
            generator = new SpecializationsGenerator();
        }
        return generator;
    }

    public String generate() {
        Random rand = new Random();
        int index = rand.nextInt(subjectList.length);
        return subjectList[index];
    }

}
