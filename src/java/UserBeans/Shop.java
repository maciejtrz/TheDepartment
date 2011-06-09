/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;

import ConnectionDataBase.Capacity;
import ConnectionDataBase.CapacityHelper;
import ConnectionDataBase.Playerresources;
import Connections.ConnectionSingleton;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pk2109
 */
public class Shop {

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

    public String submit() throws SQLException {


        Capacity capacity = capacityhelper.getCapacity(name);

        int studentsNum = Integer.parseInt(students);

        if ((studentsNum + resources.getUndergraduatesnumber()) > capacity.getStudentscapacity()) {
            System.out.println("Not enough space for studetns");
            return ("failure");
        }
        int phdsNum = Integer.parseInt(phds);

        if ((phdsNum + resources.getPhdsnumber()) > capacity.getPhdscapacity()) {
            System.out.println("Not enough space for new Phds");
            return ("failure");
        }

        int studentCost = studentsNum * 5;
        int phdCost = phdsNum * 20;

        int totalCost = studentCost + phdCost;


        if (resources.getMoney() < totalCost) {
            System.out.println("Not enought money");
            return ("failure");
        }


        resources.setMoney(resources.getMoney() - totalCost);
        resources.setUndergraduatesnumber(resources.getUndergraduatesnumber() + studentsNum);
        resources.setPhdsnumber(resources.getPhdsnumber() + phdsNum);

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
