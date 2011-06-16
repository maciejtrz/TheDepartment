package specializationsGenerator;

import ConnectionDataBase.Subject;
import ConnectionDataBase.SubjectHelper;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class SpecializationsGenerator {

    public static String[] subjectList;

    private static SpecializationsGenerator generator = null;

    public static void initializeSpecializationsGenerator() {
        SubjectHelper subjectHelper = new SubjectHelper();
        List<Subject> list = subjectHelper.getSubjectList();
        Iterator<Subject> iterator = list.iterator();

        subjectList = new String[list.size()];

        while(iterator.hasNext()) {
            Subject subject = iterator.next();
            subjectList[subject.getSubjectid()] = subject.getSubjectname();
        }
    }

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
