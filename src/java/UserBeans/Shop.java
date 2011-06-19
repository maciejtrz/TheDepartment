/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;

import ConnectionDataBase.Capacity;
import ConnectionDataBase.CapacityHelper;
import ConnectionDataBase.Playerresources;
import Connections.ConnectionSingleton;
import Connections.UserManager;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utilities.BasicUtils;

/**
 *
 * @author pk2109
 */
public class Shop implements Serializable {


    public int students;
    public int phds;
    private CapacityHelper capacityhelper;
    private String name;

    public Shop() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Auth auth = (Auth) session.getAttribute(ConnectionSingleton.auth);
        name = auth.getUsername();
        capacityhelper = new CapacityHelper();
    }



    public int getPhds() {
        return phds;
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int s) {
        this.students = s;
    }

    public void setPhds(int p) {
        this.phds = p;
    }

    public String submitphds() throws SQLException {

        Capacity capacity = capacityhelper.getCapacity(name);

        boolean error = false;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        if ((getPhds()+ UserManager.getPhds(name)) > capacity.getPhdscapacity()) {
            System.out.println("Not enough space for new Phds");

                FacesContext.getCurrentInstance().addMessage(
                BasicUtils.findComponent(facesContext.getViewRoot(),"submitStudent").getClientId(facesContext),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Have a mercy on students", "Not enought space for us!!!"));

            error = true;

            phds=0;
            return ("failure");
        }

        else if (UserManager.getMoney(name) < getPhds()*20) {
            System.out.println("Not enought money");

                FacesContext.getCurrentInstance().addMessage(
                BasicUtils.findComponent(facesContext.getViewRoot(),"submitStudent").getClientId(facesContext),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Insufficeint Money", "PhDs aren't slaves,they won't work for penny!"));

                error= true;
            phds=0;
            return ("failure");
        } else if (!error){
            
                    FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),"submitStudent").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_INFO,"Succesfull Transaction", "Transaction successful"));

        }
        int phdsCost = getPhds()* 20;

        UserManager.removeMoney(name, phdsCost);
        UserManager.addPhdsnumber(name, getPhds());

        phds=0;
        return "success";
    }

    public String submitstudents() throws SQLException {


        Capacity capacity = capacityhelper.getCapacity(name);

        boolean error = false;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        if ((getStudents() + UserManager.getStudents(name)) > capacity.getStudentscapacity()) {
            System.out.println("Not enough space for studetns");

            FacesContext.getCurrentInstance().addMessage(
                BasicUtils.findComponent(facesContext.getViewRoot(),"submitStudent").getClientId(facesContext),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Have a mercy on students", "Not enought space for us!!!"));
             error = true;
            students = 0;

            return "fail";
        }



        else if (UserManager.getMoney(name) < getStudents()*5) {
            System.out.println("Not enought money");

             FacesContext.getCurrentInstance().addMessage(
                BasicUtils.findComponent(facesContext.getViewRoot(),"submitPhd").getClientId(facesContext),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Insufficeint Money", "Students aren't slaves,they won't work for penny!"));


            error = true;
            students = 0;

           return  "fail";

        }

        else if (!error){

                    FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),"submitPhd").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_INFO,"Succesfull Transaction", "Transaction successful"));
        }


        int studentCost = getStudents() * 5;

        UserManager.removeMoney(name, studentCost);
        UserManager.addUndegraduatesnumber(name, getStudents());

        students = 0;
        return "success";

    }

    //aux methods

    public String getStudentCapacity(){

        Capacity capacity = capacityhelper.getCapacity(name);

        Integer cap = capacity.getStudentscapacity();
        return cap.toString();

    }

    public String getPhdsCapacity(){

        Capacity capacity = capacityhelper.getCapacity(name);

        Integer cap = capacity.getPhdscapacity();
        return cap.toString();

    }


    public String getBalance() throws SQLException {


        int balance = UserManager.getMoney(name);

        System.out.println(name);

        return (balance + "");
    }

    public String go() {
        return "go";
    }

    public String getStudentNumber(){
        Integer num = UserManager.getStudents(name);
        return num.toString();
    }

        public String getPhdsNumber(){
        Integer num = UserManager.getPhds(name);
        return num.toString();
    }

        public String closePage(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),"submitStudent").getClientId(facesContext),
                    null);

            return null;
        }
}
