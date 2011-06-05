package ResearchPoints;

import java.util.Vector;
import javax.faces.model.SelectItem;

public class ResearchSubject {

    private String[] subjectList = {"Artifical Intelligence", "Machine Learning",
        "Compilers", "Operating System Design", "Networks and Communication",
        "Models of Computation", "Games and Porn"
    };
    private String[] lecturerList = {"Tony Field", "Karol Pysniak", "Krzysztof Huszcza",
        "Paul Kelly", "Piotr Kraus", "Mark Zuckerberg", "Murzynek Bambo"
    };

    public ResearchSubject() {
        for (int i = 0; i < subjectList.length; i++) {
            subjects.add(new SelectItem(new Integer(i), subjectList[i]));
        }

        for (int i = 0; i < lecturerList.length; i++) {
            lecturers.add(new SelectItem(new Integer(i), lecturerList[i]));
        }

    }
    private Integer subject;  // The index of the selected item
    // can be given as String/Integer/int ...
    private Vector<SelectItem> subjects = new Vector<SelectItem>();
    private Vector<SelectItem> lecturers = new Vector<SelectItem>();


    public Vector getSubjects() {
        return subjects;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer nextSWVersion) {
        subject = nextSWVersion;
    }

    public void setLecturers(Vector chosenLecturers) {
        for(int i = 0; i < chosenLecturers.size();i++)
            System.out.println((i+1) + ": " + chosenLecturers.get(i).toString());
    }

    public Vector getLecturers() {
        return new Vector();
    }

    public Vector getLecturerList() {
        return lecturers;
    }
}
