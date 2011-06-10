package ResearchPoints;

import ConnectionDataBase.Research;
import ConnectionDataBase.ResearchHelper;
import ConnectionDataBase.ResearchId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import specializationsGenerator.SpecializationsGenerator;

public class ResearchBag {

    private List<Research> researchBag = new ArrayList<Research>();
    private List<Integer> availableResearch;
    private String username;
    private ResearchHelper researchHelper;
    private Research selectedResearch;

    public ResearchBag() {
        researchHelper = new ResearchHelper();
    }

    public void initialize(String username) {
        this.username = username;
        availableResearch = researchHelper.getAvailableResearches(username);
        researchHelper = new ResearchHelper();
    }

    public String getUserid() {
        return username;
    }

    public List<Integer> getAvailableResearch() {
        return availableResearch;
    }

    public Research getSelectedResearch() {
        return selectedResearch;
    }

    public void setSelectedResearch(Research selectedResearch) {
        System.out.println(selectedResearch.getName() + " was selected. ");

        selectedResearch.changeState();
    }

    public List<Research> getResearches() {
        return researchBag;
    }

    public void setResearches(List<Research> researchBag) {
        this.researchBag = researchBag;
    }

    public void update() {
    }

    public TreeNode getFinishedReserchTree() {

        TreeNode root = new DefaultTreeNode("Root", null);

        for (int i = 0; i < SpecializationsGenerator.subjectList.length; i++) {
            DefaultTreeNode subjectNode =
                    new DefaultTreeNode(SpecializationsGenerator.subjectList[i], root);

            List<ResearchTreeNode> list = ResearchDevelopment.getFirstResearch(i);
            Iterator<ResearchTreeNode> iterator = list.iterator();

            while (iterator.hasNext()) {
                ResearchTreeNode treeNode = iterator.next();
                if (!availableResearch.contains(treeNode.getResearchInstance().getResearchid())) {
                    setChildNodes(new DefaultTreeNode(treeNode.getResearchInstance().getResearchname(),
                            subjectNode), treeNode);
                }
            }
        }

        return root;
    }

    private void setChildNodes(DefaultTreeNode parentTreeNode, ResearchTreeNode researchTreeNode) {
        Iterator<ResearchTreeNode> iterator = researchTreeNode.getDependentResearches().iterator();

        while (iterator.hasNext()) {
            ResearchTreeNode treeNode = iterator.next();
            System.out.println("Considering research with id: "
                    + treeNode.getResearchInstance().getResearchid());

            if (!availableResearch.contains(treeNode.getResearchInstance().getResearchid())) {
                DefaultTreeNode nextParentNode =
                        new DefaultTreeNode(treeNode.getResearchInstance().getResearchname(),
                        parentTreeNode);
                setChildNodes(nextParentNode, treeNode);
            }

        }
    }

    public String manageResearch() {
        return "go";
    }
}
