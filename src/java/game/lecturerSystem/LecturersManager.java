
package game.lecturerSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LecturersManager {

    public static final int MAX_AVAILABLE = 5;

    private String userName;
    private ArrayList<Lecturer> availableLecturers;
    private ArrayList<Lecturer> ownedLecturers;

    public LecturersManager(String username) {
        availableLecturers = new ArrayList<Lecturer>();
        ownedLecturers = new ArrayList<Lecturer>();
        userName = username;
    }


    public synchronized void  repopulateAvailableLec() {
        for (int i = 0 ; i < MAX_AVAILABLE ; i++) {
            availableLecturers.add(i, generateLecturer());
        }
    }

    public synchronized ArrayList<Lecturer> getOwnedLecturers() {
        return ownedLecturers;
    }

    public synchronized ArrayList<Lecturer>getAvailableLecturers() {
        return availableLecturers;
    }

    /* returns true if successful. */
    public synchronized boolean purchaseLecturer(String lecName) {
        Lecturer lect = lookUpLecturer(lecName, availableLecturers);
        Statement statement
              = Connections.ConnectionSingleton.createConnection()
                .getStatement();
        try {
            int money = 0;
            int price = 0;
            
            /* Obtaining player's money. */
            String query = "SELECT money FROM PlayerResources WHERE"
                    + " IdName = '" + userName + "'";
            ResultSet result = statement.executeQuery(query);
            if  (result.next()) {
                money = result.getInt("money");
            }
            else {
                return false;
            }
            
            /* Obtaining lecturer's price. */
            query = "SELECT price FROM lecturers WHERE "
                    + " LecturerName = '" + lecName + "'";
            result = statement.executeQuery(query);
            if (result.next()) {
                price = result.getInt("price");
            }
            else {
                return false;
            }
            
            /* Purchasing the lecturer*/
            if (money < price) {
                return false;
            }
          
            /* Updating the database. */
            query = "UPDATE Playerresources SET money = " + (money-price) +
                    "WHERE IdName = '" + userName + "'";
            statement.executeUpdate(query);
            
            /* Deleting the lecturer from the database*/
            query = "DELETE FROM LecturersAvailable WHERE "
                + " LecturerName = '" + lecName + "'";
            statement.executeUpdate(query);

            /* Updating the lists. */
            availableLecturers.remove(lect);
            ownedLecturers.add(lect);

            return true;

        } catch (SQLException ex) {
           Logger.getLogger(LecturersManager.class.getName())
                 .log(Level.SEVERE, null, ex);
           return false;
        }

    }

    public synchronized void readLecturers() {
        try {
            Statement statement
              = Connections.ConnectionSingleton.createConnection()
                .getStatement();

            /* Reading already "owned" lecturers. */
            String query = "SELECT * FROM LecturersOwned WHERE "
                + "IdName = '" + userName + "'";
            ResultSet result = statement.executeQuery(query);

            /* Reading lecturers names. */
            ArrayList<String> lecturersNames = new ArrayList<String>();
            while (result.next()) {
                String lecturerName = result.getString("LecturerName");
                lecturersNames.add(lecturerName);
            }

            /* Creating lecturers objects. */
            Iterator<String> it = lecturersNames.iterator();
            while (it.hasNext()) {
                String name = it.next();
                addLecturer(name, ownedLecturers, statement);
            }

            /* Reading lecturers available for purchase. */
            query = "SELECT * FROM LecturersAvailable WHERE"
                    + " IdName = '" + userName + "'";
            System.out.println(query);
            result = statement.executeQuery(query);
            lecturersNames = new ArrayList<String>();

            /* Reading lecturers names. */
            while (result.next()) {
                String lecturerName = result.getString("LecturerName");
                lecturersNames.add(lecturerName);
            }

            /* Creating lecturers objects. */
            it = lecturersNames.iterator();
            while (it.hasNext()) {
                String name = it.next();
                addLecturer(name, availableLecturers, statement);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LecturersManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private Lecturer generateLecturer () {

        /* Setting up the specialization. */
        ArrayList<LecturerBenefits> specList
                = new ArrayList<LecturerBenefits>();

        LecturerBenefits lb = new LecturerBenefits("AI" , 10);
        specList.add(lb);
        return new Lecturer("Krzysztof Huszcza" , 10 , 5 , specList);
    }

    private void addLecturer(String lecturerName, ArrayList<Lecturer> list,
            Statement statement) {
        try {

            /* Obtaining atributes. */
            String query = "SELECT * FROM Lecturers WHERE "
                + "LecturerName = '" + lecturerName + "'";
            ResultSet atributes = statement.executeQuery(query);
 
            int price = 0;
            int rpContribution = 0;

            if (atributes.next()) {

            /* Populating Lecturer objects. */
                price = atributes.getInt("Price");
                rpContribution = atributes.getInt("RPContribution");
                System.out.println("Price: "
                          + price + "rpContribution: " + rpContribution);
            }

            /* Obtaining specializations. */
            query = "SELECT * FROM LecturersSpecializations"
                    + " WHERE LecturerName = '" + lecturerName + "'";
            ResultSet specializations = statement.executeQuery(query);

            ArrayList<LecturerBenefits> benefitsList 
                    = new ArrayList<LecturerBenefits>();
            
            while (specializations.next()) {
                String specialization
                    = specializations.getString("Specialization");
                int boost = specializations.getInt("Boost");
                LecturerBenefits benefits 
                        = new LecturerBenefits(specialization, boost);
                benefitsList.add(benefits);
            }
            
            Lecturer lecturer 
                = new Lecturer(lecturerName,price,rpContribution,benefitsList);
            
            list.add(lecturer);

        } catch (SQLException ex) {
            Logger.getLogger(LecturersManager.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private Lecturer lookUpLecturer (String name, ArrayList<Lecturer> list) {
        Lecturer lec = null;
        Iterator<Lecturer> it = list.iterator();
        while (it.hasNext()) {
            lec = it.next();
            if (lec.getName().equals(name)) {
                break;
            }
        }
        return lec;

    }


}
