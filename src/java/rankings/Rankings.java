/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rankings;

import ConnectionDataBase.Playerresources;
import java.util.List;

/**
 *
 * @author pk2109
 */
public class Rankings {

    public List<Playerresources> money;
    public List<Playerresources> research;
    public List<Playerresources> phds;
    public List<Playerresources> students;
    public int moneypos;
    public int researchpos;
    public int studentspos;
    public int phdspos;

    public int getPhdspos(){
        return PhDsRankingSingleton.getPlayerPosition();
    }

    public int getStudentpos(){
        return StudentsRankingSingleton.getPlayerPosition();
    }

    public int getResearchpos(){
        return ResearchPointsRankingSingleton.getPlayerPosition();
    }

    public int getMonetpos(){
        return MoneyRankingSingleton.getPlayerPosition();
    }

    public void setPhdspos(int a){
        phdspos = a;
    }

    public void setMoney(int a){
        moneypos = a;
    }

    public void setStudent(int a){
        studentspos = a;
    }

    public void setResearchpos(int a){
        researchpos = a;
    }


    public List<Playerresources> getMoney(){
        return MoneyRankingSingleton.getList();
    }

   public List<Playerresources> getResearch(){
        return ResearchPointsRankingSingleton.getList();
    }

   public List<Playerresources> getStudents(){
        return StudentsRankingSingleton.getList();
    }

   public List<Playerresources> getPhds(){
        return PhDsRankingSingleton.getList();
   }

   public void setMoney(List<Playerresources> l){
       this.money = l;
   }

   public void setResearch(List<Playerresources> l){
       this.research = l;
   }

   public void setPhds(List<Playerresources> l){
       this.phds = l;
   }

   public void setStudents(List<Playerresources> l){
      this.students = l;
   }



}
