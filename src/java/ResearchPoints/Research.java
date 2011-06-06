package ResearchPoints;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Research implements Runnable {

    /* The title of the research */
    private String name;

    /* Research points awarded to the player for successful completion */
    private int ResearchPoints;

    /* List of participating lectures */
    private List lecturers;
    /* The time of research */
    private long researchTime;
    /* The user name of the owner */
    private String userId;
    private List<Research> list;

    public Research(String name, int ResearchPoints) {
        this.name = name;
        this.ResearchPoints = ResearchPoints;
    }

    public Research(String name, int ResearchPoints, List lecturers, String userId, long time) {
        this(name, ResearchPoints);
        this.lecturers = lecturers;
        this.userId = userId;
        researchTime = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResearchPoints(int rp) {
        ResearchPoints = rp;
    }

    public String getName() {
        return name;
    }

    public int getResearchPoints() {
        return ResearchPoints;
    }

    public void addLecturer(String lecturer) {
        lecturers.add(lecturer);
    }

    public void removeLecturer(String lecturer) {
        lecturers.remove(lecturer);
    }

    public List getLecturers() {
        return lecturers;
    }

    public String getResearchTime() {
        return "" + researchTime;
    }

    public void setResearchTime(long time) {
        researchTime = time;
    }

    private String getUserId() {
        return userId;
    }

    private void addPointsToUser() throws SQLException {

        freeLectuerers();

        Statement statement = Connections.ConnectionSingleton.createConnection().getStatement();

        statement.execute("BEGIN");

        statement.execute("SELECT * FROM PlayerResources FOR UPDATE");
        statement.execute("UPDATE PlayerResources SET ResearchPoints = ResearchPoints + "
               + getResearchPoints() + " WHERE IdName = '" + getUserId() + "'");

        statement.execute("COMMIT");

    }

    public void run() {
        try {

            researchTime = 10;

            while(researchTime > 0) {
                Thread.sleep(1000);
                researchTime--;
            }               

            addPointsToUser();
            list.remove(this);

        } catch (Exception ex) {
            Logger.getLogger(Research.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void freeLectuerers() {

    }

    public void addList(List<Research> list) {
       this.list = list;
    }

    public void update() {


    }
}
