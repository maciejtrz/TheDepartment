package UserBeans;


import Connections.AuthorizationSingleton;
import java.io.IOException;
import java.io.Serializable;

public class Unlog implements Serializable {

    /** Creates a new instance of Unlog */
    public Unlog() {
    }

    public String logoff() throws IOException {

        AuthorizationSingleton.logoff();
        return "success";
    }

}

