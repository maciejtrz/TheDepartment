package UserBeans;


import Connections.AuthorizationSingleton;
import java.io.IOException;

public class Unlog {

    /** Creates a new instance of Unlog */
    public Unlog() {
    }

    public String logoff() throws IOException {

        System.out.println("Logging off...");
        AuthorizationSingleton.logoff();

        return "success";
    }

}

