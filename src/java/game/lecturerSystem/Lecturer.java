
package game.lecturerSystem;

import java.util.ArrayList;


public class Lecturer {

    private String name;
    private int price;
    private int rpContribution;
    private ArrayList<LecturerBenefits> specializations;

    public Lecturer(String name , int price, int rpContribution,
            ArrayList<LecturerBenefits> specializations) {
        this.name = name;
        this.price = price;
        this.rpContribution = rpContribution;
        this.specializations = specializations;

    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getRpContribution(){
        return rpContribution;
    }

    public ArrayList<LecturerBenefits> getSpecializations(){
        return specializations;
    }


}
