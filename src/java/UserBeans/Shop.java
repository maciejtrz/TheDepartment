/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;

import ConnectionDataBase.Capacity;
import ConnectionDataBase.CapacityHelper;
import ConnectionDataBase.Playerresources;
import Connections.ConnectionSingleton;
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

    public String playerName;
    public int students;
    public int phds;
    private Playerresources resources;
    private CapacityHelper capacityhelper;
    private String name;

    public Shop() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Auth auth = (Auth) session.getAttribute(ConnectionSingleton.Auth);

        name = auth.getUsername();
        resources = auth.getResources();
        capacityhelper = new CapacityHelper();
    }

    public void setPlayername(String s) {
        playerName = s;
    }

    public String getPlayername() {

        return playerName;
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

        if ((getPhds()+ resources.getPhdsnumber()) > capacity.getPhdscapacity()) {
            System.out.println("Not enough space for new Phds");

                FacesContext.getCurrentInstance().addMessage(
                BasicUtils.findComponent(facesContext.getViewRoot(),"submitStudent").getClientId(facesContext),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Have a mercy on students", "Not enought space for us!!!"));

            error = true;

            phds=0;
            return ("failure");
        }

        else if (resources.getMoney() < getPhds()*20) {
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

        resources.setMoney(resources.getMoney() - phdsCost);
        resources.setPhdsnumber(resources.getPhdsnumber() + getPhds());

        phds=0;
        return "success";
    }

    public String submitstudents() throws SQLException {


        Capacity capacity = capacityhelper.getCapacity(name);

        boolean error = false;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        if ((getStudents() + resources.getUndergraduatesnumber()) > capacity.getStudentscapacity()) {
            System.out.println("Not enough space for studetns");

            FacesContext.getCurrentInstance().addMessage(
                BasicUtils.findComponent(facesContext.getViewRoot(),"submitStudent").getClientId(facesContext),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Have a mercy on students", "Not enought space for us!!!"));
             error = true;
            students = 0;

            return "fail";
        }



        else if (resources.getMoney() < getStudents()*5) {
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

        resources.setMoney(resources.getMoney() - studentCost);
        resources.setUndergraduatesnumber(resources.getUndergraduatesnumber() + getStudents());

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


        int balance = resources.getMoney();

        System.out.println(this.playerName);

        return (balance + "");
    }

    public String go() {
        return "go";
    }

    public String getStudentNumber(){
        Integer num = this.resources.getUndergraduatesnumber();
        return num.toString();
    }

        public String getPhdsNumber(){
        Integer num = this.resources.getPhdsnumber();
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
