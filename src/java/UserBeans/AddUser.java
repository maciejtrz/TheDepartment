package UserBeans;

import ConnectionDataBase.PlayerHelper;
import ConnectionDataBase.PlayerresourcesHelper;
import ConnectionDataBase.ResearchHelper;
import Connections.EncodingSingleton;


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
        try {
            username = username.trim();
            password = password.trim();
            password2 = password2.trim();
            email=email.trim();

            if ( validatePassword() && validateEmail() ) {

                String encodedPassword = EncodingSingleton.encodePassword(password);
                PlayerHelper player = new PlayerHelper();
                player.addPlayer(username, encodedPassword, email);

                PlayerresourcesHelper playerResources = new PlayerresourcesHelper();
                playerResources.createPlayerResources(username);

                ResearchHelper researchHelper = new ResearchHelper();
                researchHelper.initializedResearchTree(username);

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
