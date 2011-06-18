/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.model.SelectItem;
import utilities.*;

public class TradeMessageWriter extends TradeWriter implements Serializable {

    private List<Lecturer> availableLecturers = new ArrayList();;
    private List<SelectItem> availableSelectLecturers = new ArrayList();
    private int selectedLecturer = 0;

    /** Creates a new instance of TradeMessageWriter */
    public TradeMessageWriter() {
        super(MessageSingleton.TRADE_OFFER);

        LecturersManager lecturerManager = new LecturersManager(BasicUtils.getUserName());
        availableLecturers = lecturerManager.getAvailabeLecturers();

        Iterator<Lecturer> iterator = availableLecturers.iterator();
        int i = 0;
        while(iterator.hasNext()) {
            Lecturer lecturer = iterator.next();
            availableSelectLecturers.add(new SelectItem(new Integer(i++),lecturer.getName()));
        }

    }

    public TradeMessageWriter(int messageType) {
        super(messageType);
    }

    public List<SelectItem> getLecturesAvailable() {

        return availableSelectLecturers;
    }

    public int getSelectedLecturer() {
        return selectedLecturer;
    }

    public void setSelectedLecturer(int selectedLecturer) {
        this.selectedLecturer = selectedLecturer;
    }

    /* reading offers and accepting/decling... */

}
