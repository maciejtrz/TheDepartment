
/* A utility class used to manage lecturers system.
   It is used for more complicated operations on the lecturers system,
   such as repopulating available lecturers, purchasing lecturers. For
   basic funcionality and updates to the lecturer database refer to the
   ConnectionDatabase.LecturersHelper class. */


package utilities;

import ConnectionDataBase.*;
import Connections.ConnectionSingleton;
import UserBeans.Auth;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
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
            List<LecturerBenefits> specializations
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
    
    public boolean canRemoveLecturer(int lecturerid) {
        LecturersHelper lecturersHelper = new LecturersHelper();
        String lecturerName = lecturersHelper.getLecturer(lecturerid);
        
        return canRemoveLecturer(lecturerName);
    }


    /* Checks whether a given lecturer can be removed from users owned
       lecturers. */
    public boolean canRemoveLecturer(String lecturerName) {
        ArrayList<Lecturer> ownedLecturers = getOwnedLecturers();
        Lecturer lec = lookUpLecturer(lecturerName, ownedLecturers);
        if (lec == null) {
            return false;
        }
        return (lec.getUsable());
    }

    /* Removes a given lecturer from player's owned list. */
    public boolean removeOwnedLecturer(int lecturerid) {

        LecturersHelper lecturersHelper = new LecturersHelper();
        String lecturerName = lecturersHelper.getLecturer(lecturerid);

        if (!canRemoveLecturer(lecturerName)) {
            return false;
        }

        /* Removing lecturer from the owned database*/
        LecturersOwnedHelper helper = new LecturersOwnedHelper();
        helper.deleteLecturer(lecturerName);
        return true;
    }


    /* Removes a given lecturer from player's owned list. */
    public boolean removeOwnedLecturer(String lecturerName) {
        if (!canRemoveLecturer(lecturerName)) {
            return false;
        }

        /* Removing lecturer from the owned database*/
        LecturersOwnedHelper helper = new LecturersOwnedHelper();
        helper.deleteLecturer(lecturerName);
        return true;
    }

    /* Adds lecturers to the players list if possible.*/
        public boolean addLecturer(int lecturerid) {
        ArrayList<Lecturer> ownedLecturers = getOwnedLecturers();

        LecturersHelper lecturersHelper = new LecturersHelper();
        String lecturerName = lecturersHelper.getLecturer(lecturerid);

        Lecturer lec = lookUpLecturer(lecturerName, ownedLecturers);
        if (lec != null) {
            return false;
        }

        /* Adding lecturer to the database. */
        LecturersOwnedHelper helper = new LecturersOwnedHelper();
        helper.addLecturer(lecturerName, userName);
        return true;
    }

    /* Adds lecturers to the players list if possible.*/
    public boolean addLecturer(String lecturerName) {
        ArrayList<Lecturer> ownedLecturers = getOwnedLecturers();
        Lecturer lec = lookUpLecturer(lecturerName, ownedLecturers);
        if (lec != null) {
            return false;
        }

        /* Adding lecturer to the database. */
        LecturersOwnedHelper helper = new LecturersOwnedHelper();
        helper.addLecturer(lecturerName, userName);
        return true;
    }





    /* Generates one lecturer and adds him to the player's owned list. */
    public void generateOneOwnedLecturer() {

            Lecturer newLecturer = generateLecturer();

            /* Getting all required hibernate helpers. */
            LecturersOwnedHelper lecturersOwned
                    = new LecturersOwnedHelper();
            LecturersHelper lecturersHelper
                    = new LecturersHelper();
            LecturersSpecializationsHelper specializationHelper
                    = new LecturersSpecializationsHelper();


            // Obtaining attributes.
            String name = newLecturer.getName();
            int price = newLecturer.getPrice();
            boolean usable = newLecturer.getUsable();
            List<LecturerBenefits> specializations
                    = newLecturer.getSpecializations();

            // Updating the databases.
            lecturersHelper.addLecturer(name, price, usable);
            lecturersOwned.addLecturer(name, userName);

            Iterator <LecturerBenefits> it
                    = specializations.iterator();
            while (it.hasNext()) {
              LecturerBenefits benefit = it.next();
              specializationHelper
               .setSpecialization(name, benefit.getField(), benefit.getBoost());
            }

    }
    

    /* returns true if successful. */
    public boolean purchaseLecturer(String lecName) {

        /* Getting all required hibernate helpers. */
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Auth auth = (Auth) session.getAttribute(ConnectionSingleton.auth);

        Playerresources resources
                = auth.getResources();
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
        int money = resources.getMoney();
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
        resources.setMoney(money-price);
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


    /* Updates all lecturers performance by given value. */
    public boolean updateLecturersBoost(int update_value) {

        LecturersSpecializationsHelper specializationsHelper
                = new LecturersSpecializationsHelper();
        int max_boost = LecturerBenefits.MAX_BOOST;

        ArrayList<Lecturer> owned_list = getOwnedLecturers();
        if (owned_list == null) {
            return false;
        }
        Iterator<Lecturer> it = owned_list.iterator();
        while (it.hasNext()) {
            Lecturer lec = it.next();
            List<Lecturersspecializations> spec_list =
                   specializationsHelper.getSpecializationsRecord(lec.getName());
            if (spec_list == null) {
                return false;
            }
            Iterator<Lecturersspecializations> spec_it = spec_list.iterator();
            while (spec_it.hasNext()) {
                Lecturersspecializations spec = spec_it.next();
                System.out.println("Updating boost: " + spec.getSpecialization());
                int cur_boost = spec.getBoost();
                System.out.println("Current boost: " + cur_boost);
                if (cur_boost + update_value <= max_boost) {
                    System.out.println("Update_value: "  + update_value);
                    specializationsHelper.setBoost(spec, cur_boost + update_value);
                }

            }

        }
        return true;
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
            usable = lecturer_record.getUsable();
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
