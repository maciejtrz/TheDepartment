
package game.lecturerSystem;

import ConnectionDataBase.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LecturersManager {

    public static final int MAX_AVAILABLE = 5;

    private String userName;
    private ArrayList<Lecturer> availableLecturers;
    private ArrayList<Lecturer> ownedLecturers;

    public LecturersManager(String username) {
        availableLecturers = new ArrayList<Lecturer>();
        ownedLecturers = new ArrayList<Lecturer>();
        userName = username;
    }


    public synchronized void  repopulateAvailableLec() {
        for (int i = 0 ; i < MAX_AVAILABLE ; i++) {
            Lecturer newLecturer = generateLecturer();

            /* Getting all required hibernate helpers. */
            LecturersAvailableHelper lecturersAvHelper
                    = new LecturersAvailableHelper();
            LecturersHelper lecturersHelper
                    = new LecturersHelper();
            LecturersSpecializationsHelper specializationHelper
                    = new LecturersSpecializationsHelper();


            // Obtaining attributes.
            String name = newLecturer.getName();
            int price = newLecturer.getPrice();
            boolean usable = newLecturer.getUsable();
            ArrayList<LecturerBenefits> specializations
                    = newLecturer.getSpecializations();

            // Updating the databases.
            lecturersHelper.addLecturer(name, price, usable);
            lecturersAvHelper.addLecturer(name, userName);

            Iterator <LecturerBenefits> it
                    = specializations.iterator();
            while (it.hasNext()) {
                LecturerBenefits benefit = it.next();
                specializationHelper
                     .setSpecialization(name, benefit.getField(), benefit.getBoost());
            }

            availableLecturers.add(i, newLecturer);


        }

    }

    public synchronized ArrayList<Lecturer> getOwnedLecturers() {
        return ownedLecturers;
    }

    public synchronized ArrayList<Lecturer>getAvailableLecturers() {
        return availableLecturers;
    }

    /* returns true if successful. */
    public synchronized boolean purchaseLecturer(String lecName) {
        Lecturer lect = lookUpLecturer(lecName, availableLecturers);

        /* Getting all required hibernate helpers. */
        PlayerresourcesHelper resourcesHelper
                = new PlayerresourcesHelper();
        LecturersHelper lecturersHelper
                = new LecturersHelper();
        LecturersAvailableHelper lecturersAvHelper
                = new LecturersAvailableHelper();
        LecturersOwnedHelper lecturersOwnedHelper
                =  new LecturersOwnedHelper();

        // Obtaining players money.
        int money = resourcesHelper.getMoney(userName);
        // Obtaining lecturers price.
        int price = 0;

        Lecturers lecturer = lecturersHelper.getLecturer(lecName);
        if (lecturer != null) {
            price = lecturer.getPrice();
        }
        // Purchasing the lecturer if possible.
        if (price > money) {
            return false;
        }

        System.out.println("Money " + money + " price " + price);

        // Updating the database.
        resourcesHelper.updateMoney(userName, (money-price));
        lecturersAvHelper.deleteLecturer(lecName);
        lecturersOwnedHelper.addLecturer(lecName, userName);

        /* Updating session lists. */
        availableLecturers.remove(lect);
        ownedLecturers.add(lect);

        return true;
    }


    public synchronized void readLecturers() {

        // Getting all required hibernate helpers.
        LecturersAvailableHelper lecturersAvHelper
                = new LecturersAvailableHelper();
        LecturersOwnedHelper lecturersOwnedHelper
                =  new LecturersOwnedHelper();

        // Reading all "already owned" lecturers.
        List<Lecturersowned> owned_lects
                = lecturersOwnedHelper.getLecturersList(userName);
        Iterator <Lecturersowned> it = owned_lects.iterator();
        while (it.hasNext()) {
            Lecturersowned lect = it.next();
            addLecturer(lect.getLecturername() , ownedLecturers);
        }

        // Reading all "available" lecturers.
        List<Lecturersavailable> av_lects
                = lecturersAvHelper.getLecturersList(userName);
        Iterator <Lecturersavailable> iterator = av_lects.iterator();
        while (iterator.hasNext()) {
            Lecturersavailable lect = iterator.next();
            addLecturer(lect.getLecturername(), availableLecturers);
        }

    }



    private Lecturer generateLecturer () {

        /* Setting up the specialization. */
        ArrayList<LecturerBenefits> specList
                = new ArrayList<LecturerBenefits>();

        LecturerBenefits lb = new LecturerBenefits("AI" , 10);
        specList.add(lb);
        return new Lecturer("Krzysztof Huszcza" , 10 , true , specList);
    }

    private void addLecturer(String lecturerName, ArrayList<Lecturer> list) {
        /* Adds a lecturer to the input list, reading all neccessary data
           from the database. */

        // Getting all required hibernate helpers. 
        LecturersHelper lecturersHelper = new LecturersHelper();
        LecturersSpecializationsHelper specializationsHelper
                = new LecturersSpecializationsHelper();

        // Default values.
        int price = 0;
        boolean usable = true;
        String specialization = "default";
        int boost = 0;
        ArrayList<LecturerBenefits> benefitsList 
               = new ArrayList<LecturerBenefits>();
         
        // Obtaining atributes
        Lecturers lecturer_record = lecturersHelper.getLecturer(lecturerName);
        if (lecturer_record != null) {
            price = lecturer_record.getPrice();
            usable = lecturer_record.isUsable();
        }

        // Obtaining specializations
        List<Lecturersspecializations> specializations
                = specializationsHelper.getSpecializationsRecord(lecturerName);
        Iterator <Lecturersspecializations> it = specializations.iterator();
        while (it.hasNext()) {
            Lecturersspecializations spec = it.next();
            specialization = spec.getSpecialization();
            boost = spec.getBoost();
            benefitsList.add(new LecturerBenefits(specialization,boost));
        }

        // Creating Lecturer object
        Lecturer lecturer
              = new Lecturer(lecturerName,price,usable,benefitsList);
        list.add(lecturer);

    }

    public Lecturer lookUpLecturer (String name, ArrayList<Lecturer> list) {
        Lecturer lec = null;
        Iterator<Lecturer> it = list.iterator();
        while (it.hasNext()) {
            lec = it.next();
            if (lec.getName().equals(name)) {
                break;
            }
        }
        return lec;

    }

    public void setUsable(Lecturer lecturer , boolean usable) {
       String lecturerName = lecturer.getName();
       LecturersHelper lecturersHelper = new LecturersHelper();
       Lecturers lecturer_record = lecturersHelper.getLecturer(lecturerName);
       lecturer_record.setUsable(usable);
       lecturer.setUsable(usable);
    }


}
