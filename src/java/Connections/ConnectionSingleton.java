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


    /*
    private final String connectionURL = "jdbc:postgresql://db:5432/g1027104_u";
    private final String DataBaseDriver = "org.postgresql.Driver";
    private final String username = "g1027104_u";
    private final String dbPassword = "EldyDqOUXi";

     *
     */
    
    private final String connectionURL = "jdbc:postgresql://localhost:5432/postgres";
    private final String DataBaseDriver = "org.postgresql.Driver";
    private final String username = "postgres";
    private final String dbPassword = "postgres";
 


    public static final String idname = "idname";
    public static final String password = "password";
    public static final String loggedIn = "loggedin";
    public static final String researchPoints = "researchpoints";
    public static final String playerResources = "PlayerResources";
    public static final String researchName = "researchName";
    public static final String buyLecturers ="buyLecturers";

    public static final String addTradeMessageReader = "/TheDepartment/AddTradeReader.jsp";
    public static final String addTradeMessageWriter = "/TheDepartment/AddTradeWriter.jsp";
    public static final String addAuth = "/TheDepartment/AddAuth.jsp";
    public static final String addResearchBag = "/TheDepartment/AddResearchBag.jsp";
    public static final String addBuyLecturers = "/TheDepartment/AddBuyLecturers.jsp";
    public static final String addResearchPage = "/TheDepartment/AddResearch.jsp";

    public static final String addResearch = "addResearch";
    public static final String auth = "auth";
    public static final String researchBag = "researchBag";
    public static final String tradeMessageReader = "tradeMessageReader";
    public static final String tradeMessageWriter = "tradeMessageWriter";
    public static final String buildingBean = "buildingbean";

    public static final String gameName = "/TheDepartment";
    public static final String AddDepartment = "Welcome.xhtml";
    public static final String welcomePage = gameName + "/faces/Logged/" + AddDepartment;
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