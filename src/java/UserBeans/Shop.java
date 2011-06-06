/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;

import Connections.ConnectionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pk2109
 */
public class Shop {

    private String playerName;
    public String students;
    public String phds;

    // public int studentsNum;
    //  public int phdsNum;
    public Shop() {
    }

    public String getPhds() {
        return phds;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String s) {
        this.students = s;
    }

    public void setPhds(String p) {
        this.phds = p;
    }

    public String submit() throws SQLException {


        int studentsNum = Integer.parseInt(students);
        int phdsNum = Integer.parseInt(phds);

        int studentCost = studentsNum * 5;
        int phdCost = phdsNum * 20;

        int totalCost = studentCost + phdCost;

        ConnectionSingleton connection =
                ConnectionSingleton.createConnection();
        Statement statement = connection.getStatement();

        String query = "SELECT Money FROM PlayerResources WHERE IdName = '"
                + getName() + "'";


        ResultSet result = statement.executeQuery(query);
        int currentBallance = Integer.parseInt(result.getString("Money"));
        /* if (result.next()) {
        currentBallance = Integer.parseInt(result.getString("Money"));
        }
        else {
        System.out.println("Krzyskowi siusiak odchyla sie w lewo ");
        }*/
        currentBallance -= totalCost;

        /* UPDATE suppliers
        SET city = 'Santa Clara'
        WHERE supplier_name = 'NVIDIA'; */

        String newquery = " UPDATE PlayerResources SET Money = '" + currentBallance + "' WHERE IdName = '" + getName() + "';";
        statement.executeUpdate(newquery);

        query = "SELECT UndergraduetesNumber FROM PlayerResources WHERE IdName = '"
                + playerName + "'";

        result = statement.executeQuery(query);
        int quantity = Integer.parseInt(result.getString("UndergraduetesNumber"));
        quantity += studentsNum;

        newquery = " UPDATE PlayerResources SET UndergraduatesNumber = '" + quantity + "' WHERE IdName = '" + getName() + "';";
        statement.executeUpdate(newquery);

        query = "SELECT UndergraduetesNumber FROM PlayerResources WHERE IdName = '"
                + playerName + "'";

        result = statement.executeQuery(query);

        quantity = Integer.parseInt(result.getString("PhDsNumber"));
        quantity += phdsNum;

        newquery = " UPDATE PlayerResources SET PhDsNumber = '" + quantity + "' WHERE IdName = '" + getName() + "';";
        statement.executeUpdate(newquery);

        statement.close();

        return "success";

    }

    //aux methods
    private void setName(String s) {
        playerName = s;
    }

    private String getName() {
        return playerName;
    }

    public String getBalance(String playerName) throws SQLException {

        setName(playerName);

        ConnectionSingleton connection =
                ConnectionSingleton.createConnection();
        Statement statement = connection.getStatement();
        String query = "SELECT Money FROM PlayerResources WHERE IdName = '"
                + playerName + "'";

        ResultSet result = statement.executeQuery(query);
        if (result.next()) {
            return result.getString("Money");
        } else {
            return ("Krzyskowi siusiak odchyla sie w lewo ");
        }
    }

    public String go() {
        return "go";
    }
}