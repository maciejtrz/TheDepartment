/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ResearchPoints;

import ConnectionDataBase.Researchcatalogue;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kp1209
 */
public class ResearchTreeNode {

    private List<ResearchTreeNode> dependentResearches;
    private Researchcatalogue researchInstance;
    private int uniqueId;

    public ResearchTreeNode() {
        dependentResearches = new ArrayList<ResearchTreeNode>();
    }

    public ResearchTreeNode(Researchcatalogue instance) {
        this();
        researchInstance = instance;
    }

    public void setResearchInstance(Researchcatalogue instance) {
        researchInstance = instance;
    }

    public Researchcatalogue getResearchInstance() {
        return researchInstance;
    }

    public void addResearchTreeNode(ResearchTreeNode researchNode) {
        dependentResearches.add(researchNode);
    }

    public List<ResearchTreeNode> getDependentResearches() {
        return dependentResearches;
    }




}
