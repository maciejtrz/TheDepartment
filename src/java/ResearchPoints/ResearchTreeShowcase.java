/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ResearchPoints;

import java.util.Iterator;
import java.util.List;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import specializationsGenerator.SpecializationsGenerator;

/**
 *
 * @author root
 */
public class ResearchTreeShowcase {

    private static TreeNode root;

    public static TreeNode getRoot() {

        return root;
    }

    public static void initializeResearchTreeShowcase() {

        root = new DefaultTreeNode("Root", null);

        for (int i = 0; i < SpecializationsGenerator.subjectList.length; i++) {
            DefaultTreeNode subjectNode =
                    new DefaultTreeNode(SpecializationsGenerator.subjectList[i], root);

            List<ResearchTreeNode> list = ResearchDevelopment.getFirstResearch(i);
            Iterator<ResearchTreeNode> iterator = list.iterator();

            while (iterator.hasNext()) {
                ResearchTreeNode treeNode = iterator.next();
                setChildNodes(new DefaultTreeNode(treeNode.getResearchInstance().getResearchname(),
                        subjectNode), treeNode);
            }
        }

    }

    private static void setChildNodes(DefaultTreeNode researchNode, ResearchTreeNode researchTreeNode) {
        Iterator<ResearchTreeNode> iterator = researchTreeNode.getDependentResearches().iterator();

        while (iterator.hasNext()) {
            ResearchTreeNode treeNode = iterator.next();
            setChildNodes(new DefaultTreeNode(treeNode.getResearchInstance().getResearchname(),
                    researchNode), treeNode);
        }
    }
}
