
package game.lecturerSystem;

import java.util.ArrayList;


public class Lecturer {

    private String name;
    private int price;
    private boolean usable;
    private ArrayList<LecturerBenefits> specializations;

    public Lecturer(String name , int price, boolean usable,
            ArrayList<LecturerBenefits> specializations) {
        this.name = name;
        this.price = price;
        this.usable = usable;
        this.specializations = specializations;

    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean getUsable(){
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public ArrayList<LecturerBenefits> getSpecializations(){
        return specializations;
    }


}
