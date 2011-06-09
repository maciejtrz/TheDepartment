/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ResearchPoints;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kp1209
 */
public class ResearchTreeNode {

    List<ResearchTreeNode> dependentResearches = new ArrayList<ResearchTreeNode>();
    private ResearchInstance researchInstance;
    private int uniqueId;

    public ResearchTreeNode() {

    }

    public ResearchTreeNode(ResearchInstance instance) {
        this();
        researchInstance = instance;
    }

    public void setResearchInstance(ResearchInstance instance) {
        researchInstance = instance;
    }

    public ResearchInstance getResearchInstance() {
        return researchInstance;
    }




}
