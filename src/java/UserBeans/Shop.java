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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pk2109
 */
public class Shop implements Serializable {

    public String playerName;
    public String students;
    public String phds;
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

    public String submitphds() throws SQLException {

        Capacity capacity = capacityhelper.getCapacity(name);

        int phdsNum = Integer.parseInt(phds);

        if ((phdsNum + resources.getPhdsnumber()) > capacity.getPhdscapacity()) {
            System.out.println("Not enough space for new Phds");
            return ("failure");
        }
        int phdsCost = phdsNum * 20;


        if (resources.getMoney() < phdsCost) {
            System.out.println("Not enought money");
            return ("failure");
        }

        resources.setMoney(resources.getMoney() - phdsCost);
        resources.setPhdsnumber(resources.getPhdsnumber() + phdsNum);

        return "success";
    }

    public String submitstudents() throws SQLException {


        Capacity capacity = capacityhelper.getCapacity(name);

        int studentsNum = Integer.parseInt(students);

        if ((studentsNum + resources.getUndergraduatesnumber()) > capacity.getStudentscapacity()) {
            System.out.println("Not enough space for studetns");
            return ("failure");
        }

        int studentCost = studentsNum * 5;

        if (resources.getMoney() < studentCost) {
            System.out.println("Not enought money");
            return ("failure");
        }

        resources.setMoney(resources.getMoney() - studentCost);
        resources.setUndergraduatesnumber(resources.getUndergraduatesnumber() + studentsNum);

        return "success";

    }

    //aux methods
    public String getBalance() throws SQLException {


        int balance = resources.getMoney();

        System.out.println(this.playerName);

        return (balance + "");
    }

    public String go() {
        return "go";
    }
}
