package ResearchPoints;

import java.util.Vector;
import javax.faces.model.SelectItem;


public class ResearchSubject {

    private String[] subject = {"Artifical Intelligence", "Machine Learning",
    "Compilers","Operating System Design", "Networks and Communication",
    "Models of Computation", "Games and Porn"
    };


    public ResearchSubject() {
    for(int i = 0; i < subject.length;i++)
            nextSWVersionItems.add(new SelectItem(new Integer(i),subject[i]));
    }

    private Integer nextSWVersion;  // The index of the selected item
                                // can be given as String/Integer/int ...
private Vector<SelectItem> nextSWVersionItems = new Vector<SelectItem>();

// Add the following methods
public Vector getNextSWVersionItems()
{
return nextSWVersionItems;
}


public Integer getNextSWVersion()
{
return nextSWVersion;
}


public void setNextSWVersion(Integer nextSWVersion)
{
this.nextSWVersion = nextSWVersion;
}

}

