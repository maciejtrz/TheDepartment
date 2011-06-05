package ResearchPoints;

import java.util.List;


public class Research {

    /* The title of the research */
    private String name;

    /* Research points awarded to the player for successful completion */
    private int ResearchPoints;

    /* List of participating lectures */
    private List lecturers;

    public Research(String name, int ResearchPoints) {
        this.name = name;
        this.ResearchPoints = ResearchPoints;
    }

    public Research(String name, int ResearchPoints, List lecturers) {
        this(name, ResearchPoints);
        this.lecturers = lecturers;
    }

    public void setName(String name) { this.name = name; }
    public void setResearchPoints(int rp) { ResearchPoints = rp; }

    public String getName() { return name; }
    public int getResearchPoints() { return ResearchPoints; }

    public void addLecturer(String lecturer) { lecturers.add(lecturer); }
    public void removeLecturer(String lecturer) { lecturers.remove(lecturer); }
    public List getLecturers() { return lecturers; }

    
}
