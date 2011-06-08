/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;

import ConnectionDataBase.Playerresources;
import ConnectionDataBase.PlayerresourcesHelper;
import Connections.ConnectionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private PlayerresourcesHelper reshelper;

    // public int studentsNum;
    //  public int phdsNum;
    public Shop(){
        this.reshelper = new PlayerresourcesHelper();
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

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String name = (String) session.getAttribute(ConnectionSingleton.idname);


        Playerresources resources = reshelper.getResources(name);

        int studentsNum = Integer.parseInt(students);
        int phdsNum = Integer.parseInt(phds);

        int studentCost = studentsNum * 5;
        int phdCost = phdsNum * 20;

        int totalCost = studentCost + phdCost;


         System.out.println("Here 2");

        if(resources.getMoney()<totalCost){
            System.out.println("Not enought money");
            return("failure");
        }


        reshelper.updateMoney(name, resources.getMoney()-totalCost);
        System.out.println("Here 4");
        reshelper.updateUndergraduatesnumber(name, resources.getUndergraduatesnumber()+studentsNum);
        reshelper.updatePhdsNumber(name, resources.getPhdsnumber()+phdsNum);

        return "success";

    }

    //aux methods


    public String getBalance() throws SQLException {


        int balance = reshelper.getMoney(this.playerName);

        System.out.println(this.playerName);

        return(balance+"");
    }

    public String go() {
        return "go";
    }
}