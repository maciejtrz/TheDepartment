package ResearchPoints;

import specializationsGenerator.SpecializationsGenerator;

/**
 *
 * @author kp1209
 */
public class ResearchTree {

    private ResearchTreeNode[] researchNodes;

    public ResearchTree() {

        for(int i = 0;i < SpecializationsGenerator.subjectList.length;i++) {
            researchNodes[i] = new ResearchTreeNode();
        }
    }



}
