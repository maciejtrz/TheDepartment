package Connections;

import java.sql.*;

public class ConnectionSingleton {


    private ConnectionSingleton() {
    }

    public static ConnectionSingleton createConnection() {
        if (con == null) {
            con = new ConnectionSingleton();
        }
        return con;
    }
    private static ConnectionSingleton con;
    private static Connection connection;

   
    private final String connectionURL = "jdbc:postgresql://db:5432/g1027104_u";
    private final String DataBaseDriver = "org.postgresql.Driver";
    private final String username = "g1027104_u";
    private final String dbPassword = "EldyDqOUXi"; 

    /*
    private final String connectionURL = "jdbc:postgresql://localhost:5432/postgres";
    private final String DataBaseDriver = "org.postgresql.Driver";
    private final String username = "postgres";
    private final String dbPassword = "postgres";
    */
    

    public static final String idname = "idname";
    public static final String password = "password";
    public static final String loggedIn = "loggedin";
    public static final String researchPoints = "researchpoints";
    public static final String LECTURERSMANAGER = "mgr";
    public static final String playerResources = "PlayerResources";
    public static final String addAuth = "/TheDepartment/AddAuth.jsp";

    public static final String Auth = "Auth";
    public static final String researchBag = "researchBag";

    public static final String gameName = "/TheDepartment";
    public static final String welcomePage = gameName + "/faces/Logged/WelcomePage.jsp";
    public static final String indexPage = gameName + "/faces/Unlogged/index.xhtml";

    public Statement getStatement() {

        Statement statement = null;
        try {
            if (connection == null) {
                Class.forName(DataBaseDriver);
                connection = DriverManager.getConnection(connectionURL, username, dbPassword);
            }

            statement = connection.createStatement();

        } catch (Exception e) {
            System.err.println(e.toString());
        }

        return statement;
    }
}
