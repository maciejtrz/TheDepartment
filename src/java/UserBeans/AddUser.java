package UserBeans;

import Connections.EncodingSingleton;
import java.sql.Statement;


public class AddUser {

    /** Creates a new instance of AddUser */
    public AddUser() {
    }
    private String username;
    private String password;
    private String password2;
    private String email;


    public String getPassword() {
        return password;
    }

    public String getId() {
        return username;
    }

    public String getPassword2(){
        return password2;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword2(String pass){
        password2 = pass;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public void setId(String ID) {
        username = ID;
    }

    public String insert() {

        System.err.println("Inserting...");

        try {
            Statement statement = Connections.ConnectionSingleton.createConnection().getStatement();

            username = username.trim();
            password = password.trim();
            password2 = password2.trim();
            email=email.trim();

            if ( validatePassword() && validateEmail() ) {
            // if password == password2 && email has @ the insert the record
            // to database

            String encodedPassword = EncodingSingleton.encodePassword(password);
            String query = "INSERT INTO Players VALUES('" + username + "', '" + encodedPassword + "', '" + email + "',false)";
            System.out.println("Query is: " + query);
            statement.execute(query);

            }

            else {
                System.out.println("Password or email incorrect");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return "success";
    }


    // Auxilary methods

    private boolean validatePassword(){
        return getPassword().equals(password2);
    }

    private boolean validateEmail(){
        return getEmail().contains("@");
    }

    public String goToIndex() { return "go"; }
}
