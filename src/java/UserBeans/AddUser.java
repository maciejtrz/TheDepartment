package UserBeans;

import ConnectionDataBase.PlayerHelper;
import ConnectionDataBase.PlayerresourcesHelper;
import ConnectionDataBase.ResearchHelper;
import Connections.EncodingSingleton;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import utilities.BasicUtils;

public class AddUser implements Serializable {

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

    public String getPassword2() {
        return password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword2(String pass) {
        password2 = pass;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public void setId(String ID) {
        username = ID;
    }

    public String insert() {
        boolean error = false;

        try {
            username = username.trim();
            password = password.trim();
            password2 = password2.trim();
            email = email.trim();

            FacesContext facesContext = FacesContext.getCurrentInstance();

            PlayerHelper playerHelper = new PlayerHelper();

            if (username.length() == 0) {
                System.out.println("Login is empty!");

                FacesContext.getCurrentInstance().addMessage(
                        BasicUtils.findComponent(facesContext.getViewRoot(), "username").getClientId(facesContext),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login error", "Login is required!"));

                error = true;
            } else if (playerHelper.existsPlayer(username)) {
                FacesContext.getCurrentInstance().addMessage(
                        BasicUtils.findComponent(facesContext.getViewRoot(), "username").getClientId(facesContext),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login error", "There is already user name " + username + "!"));

                error = true;
            }

            if (password.length() == 0) {
                FacesContext.getCurrentInstance().addMessage(
                        BasicUtils.findComponent(facesContext.getViewRoot(), "password").getClientId(facesContext),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password error", "Password is required!"));

                error = true;
            }

            if (password2.length() == 0) {
                FacesContext.getCurrentInstance().addMessage(
                        BasicUtils.findComponent(facesContext.getViewRoot(), "password2").getClientId(facesContext),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Repeated password error", "Repeated password is required!"));

                error = true;
            }

            if (email.length() == 0) {
                FacesContext.getCurrentInstance().addMessage(
                        BasicUtils.findComponent(facesContext.getViewRoot(), "email").getClientId(facesContext),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email error", "Email is required!"));

                error = true;
            }

            if (email.length() != 0 && !validateEmail()) {
                FacesContext.getCurrentInstance().addMessage(
                        BasicUtils.findComponent(facesContext.getViewRoot(), "email").getClientId(facesContext),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email error type", "Incorrect email!"));

                error = true;
            }

            if (password.length() != 0 && password2.length() != 0) {
                if (validatePassword()) {

                    String encodedPassword = EncodingSingleton.encodePassword(password);
                    PlayerHelper player = new PlayerHelper();
                    player.addPlayer(username, encodedPassword, email);

                    PlayerresourcesHelper playerResources = new PlayerresourcesHelper();
                    playerResources.createPlayerResources(username);

                    ResearchHelper researchHelper = new ResearchHelper();
                    researchHelper.initializedResearchTree(username);


                } else {
                    FacesContext.getCurrentInstance().addMessage(
                            BasicUtils.findComponent(facesContext.getViewRoot(), "addUserButton").getClientId(facesContext),
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do nat match", "Passwords do not match!"));

                    error = true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        String result = null;
        if (!error) {
            result = "success";
        }

        return result;
    }

    // Auxilary methods
    private boolean validatePassword() {
        return getPassword().equals(password2);
    }

    private boolean validateEmail() {
        return getEmail().contains("@");
    }

    public String goToIndex() {
        return "go";
    }
}
