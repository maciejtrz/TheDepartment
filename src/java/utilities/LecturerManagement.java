/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import ConnectionDataBase.LecturersOwnedHelper;
import ConnectionDataBase.Lecturersowned;
import java.util.List;


/**
 *
 * @author pk2109
 */
public class LecturerManagement {

    private Lecturer selected_lecturer;
    private List<Lecturer> owned;
    public LecturerManagement() {
        
        String name = BasicUtils.getUserName();
        LecturersManager mgr = new LecturersManager(name);
        owned = mgr.getOwnedLecturers();
    }

    public void setSelected_lecturer(Lecturer l){
        this.selected_lecturer=l;
    }

    public Lecturer getSelected_lecturer(){
        return this.selected_lecturer;
    }


    public void setOwned(List<Lecturer> list){
        this.owned=list;
    }

    public List<Lecturer> getOwned(){
        return this.owned;
    }

    public void delete(Lecturer l){
        LecturersManager mgr = new LecturersManager(BasicUtils.getUserName());
        LecturersOwnedHelper help = new LecturersOwnedHelper();
        help.deleteLecturer(l.getName());
        owned = mgr.getAvailabeLecturers();

    }

}
