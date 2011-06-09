
/* A utility class used to manage lecturers system.
   It is used for more complicated operations on the lecturers system,
   such as repopulating available lecturers, purchasing lecturers. For
   basic funcionality and updates to the lecturer database refer to the
   ConnectionDatabase.LecturersHelper class. */


package utilities;

import ConnectionDataBase.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import nameGenerator.NamesGenerator;
import specializationsGenerator.SpecializationsGenerator;


public class LecturersManager {


    private String userName;
    private static final int MAX_AVAILABLE = 5;

    public LecturersManager(String username) {
        userName = username;
    }


    public void setUserName(String username) {
        this.userName = username;
    }

    public String getUserName() {
        return userName;
    }


    public void  repopulateAvailableLec() {
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

        }

    }
    

    /* returns true if successful. */
    public boolean purchaseLecturer(String lecName) {

        /* Getting all required hibernate helpers. */
        PlayerresourcesHelper resourcesHelper
                = new PlayerresourcesHelper();
        LecturersHelper lecturersHelper
                = new LecturersHelper();
        LecturersAvailableHelper lecturersAvHelper
                = new LecturersAvailableHelper();
        LecturersOwnedHelper lecturersOwnedHelper
                =  new LecturersOwnedHelper();
        CapacityHelper capacityHelper
                = new CapacityHelper();

        // Checking whether the player is allowed to buy
        // any lecturers.
        Capacity capacityRecord = capacityHelper.getCapacity(userName);
        int profCapacity = capacityRecord.getProfessorscapacity();
        if (profCapacity <= 0) {
            return false;
        }

        // Obtaining players money.
        int money = resourcesHelper.getMoney(userName);
        // Obtaining lecturers price.
        int price = 0;

        Lecturers lecturer = lecturersHelper.getLecturer(lecName);
        if (lecturer == null) {
            return false;
        }

        price = lecturer.getPrice();
        // Purchasing the lecturer if possible.
        if (price > money) {
            return false;
        }

        System.out.println("Money " + money + " price " + price);

        // Updating the database.
        resourcesHelper.updateMoney(userName, (money-price));
        lecturersAvHelper.deleteLecturer(lecName);
        lecturersOwnedHelper.addLecturer(lecName, userName);

        return true;
    }

    public ArrayList<Lecturer> getAvailabeLecturers() {
        /* A helper class used to available lecturers
           of a given player. It puts the result into two input lists. */

        ArrayList<Lecturer> availableLecturers
                = new ArrayList<Lecturer>();

        // Getting all required hibernate helpers.
        LecturersAvailableHelper lecturersAvHelper
                = new LecturersAvailableHelper();

        // Reading all "available" lecturers.
        List<Lecturersavailable> av_lects
                = lecturersAvHelper.getLecturersList(userName);
        Iterator <Lecturersavailable> iterator = av_lects.iterator();
        while (iterator.hasNext()) {
            Lecturersavailable lect = iterator.next();
            addLecturer(lect.getLecturername(), availableLecturers);
        }

        return availableLecturers;
    }


    public ArrayList<Lecturer> getOwnedLecturers() {
        /* A helper class used to read owned lecturers
           of a given player. It puts the result into two input lists. */

        ArrayList<Lecturer> ownedLecturers
                = new ArrayList<Lecturer>();

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

        return ownedLecturers;
    }


    public void readLecturers(ArrayList<Lecturer> ownedLecturers,
            ArrayList<Lecturer> availableLecturers) {
        /* A helper class used to read owned lecturers and available lecturers
           of a given player. It puts the result into two input lists. */

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


    private Lecturer generateLecturer () {

        SpecializationsGenerator specGenerator = null;
        NamesGenerator nameGenerator = null;

        specGenerator
             = SpecializationsGenerator.createSpecializationsGenerator();
        nameGenerator
             = NamesGenerator.createNamesGenerator();

        /* Generating name. */
        String name = nameGenerator.generateName();

        /* Generating all atributes. */
        Random rand = new Random();
        int boost = rand.nextInt(10);
        int price = rand.nextInt(20) * 100;
        boolean usable = true;

        /* Generating specialization. */
        String specialization
                = specGenerator.generate();

        /* Setting up the specialization. */
        ArrayList<LecturerBenefits> specList
                = new ArrayList<LecturerBenefits>();

        LecturerBenefits lb = new LecturerBenefits(specialization , boost);
        specList.add(lb);
        return new Lecturer(name , price , usable , specList);
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
        if (specializations != null) {
            Iterator <Lecturersspecializations> it = specializations.iterator();
            while (it.hasNext()) {
                Lecturersspecializations spec = it.next();
                specialization = spec.getSpecialization();
                boost = spec.getBoost();
                benefitsList.add(new LecturerBenefits(specialization,boost));
            }
        }

        // Creating Lecturer object
        Lecturer lecturer
              = new Lecturer(lecturerName,price,usable,benefitsList);
        list.add(lecturer);

    }
}
