/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package resources;

import ConnectionDataBase.LecturersHelper;
import ConnectionDataBase.Playerresources;
import utilities.LecturersManager;

/**
 *
 * @author kp1209
 */
public class LecturerResource implements Resource {

    private static final String resourceName = "Lecturer";

    private LecturersManager lecturersManager;
    private LecturersHelper lecturersHelper;
    private String lecturerName;

    
    public String getLecturerName() {
        return lecturerName;
    } 
    
    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
        lecturersManager = new LecturersManager(lecturerName);

    }

    @Override
    public String getResourcesName() {
        return "Lecturer";
    }

    @Override
    public void remove(Playerresources resources, int amount) {
      lecturersManager = new LecturersManager(resources.getIdname());
      lecturersManager.removeOwnedLecturer(amount);
    }

    @Override
    public void add(Playerresources resources, int amount) {
        lecturersManager = new LecturersManager(resources.getIdname());
        lecturersManager.addLecturer(amount);
    }

    public boolean canRemove(Playerresources resources, int amount) {
        lecturersManager = new LecturersManager(resources.getIdname());
        return lecturersManager.canRemoveLecturer(amount);
    }

}
