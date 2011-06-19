package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/* A helper class used to carry and read information. Has no significant uses.*/
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

    public List<LecturerBenefits> getSpecializations(){
        return specializations;
    }

    public boolean hasSpecialization(String specialization) {
        Iterator<LecturerBenefits> it = specializations.iterator();
        while (it.hasNext()) {
            LecturerBenefits benefit = it.next();
            if (benefit.getField().equals(specialization)) {
                return true;
            }
        }
        return false;
    }


}
