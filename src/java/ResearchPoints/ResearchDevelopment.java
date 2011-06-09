/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ResearchPoints;

import ConnectionDataBase.Researchcatalogue;
import ConnectionDataBase.ResearchcatalogueHelper;
import ConnectionDataBase.Researchdependencies;
import ConnectionDataBase.ResearchdependenciesHelper;
import ConnectionDataBase.ResearchdependenciesId;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import specializationsGenerator.SpecializationsGenerator;

/**
 *
 * @author kp1209
 */
public class ResearchDevelopment {

    private static Set<ResearchInstance> reseachInstanceBag = new HashSet<ResearchInstance>();
    private static ResearchTree researchTree = new ResearchTree();
    private static ResearchTreeNode researchNodes[];

    public static void initializeDevelopmentTree() {

        ResearchcatalogueHelper researchCatalogueHelper = new ResearchcatalogueHelper();
        List<Researchcatalogue> list = researchCatalogueHelper.getResearchCatalogue();

        researchNodes = new ResearchTreeNode[list.size()];

        /* Initializing array researchNodes */
        Iterator<Researchcatalogue> iterator = list.iterator();
        while(iterator.hasNext()) {
            Researchcatalogue rc = iterator.next();
            researchNodes[rc.getResearchid()-1] =
                    new ResearchTreeNode(new ResearchInstance(rc.getResearchname()
                    ,rc.getResearchsubject(),rc.getResearchdescription()));
        }

        /* Setting dependencies between researches */
        ResearchdependenciesHelper dependenciesFinder = new ResearchdependenciesHelper();
        iterator = list.iterator();

        while(iterator.hasNext()) {
            Researchcatalogue researchcatalogue = iterator.next();
            List<Researchdependencies> dependencies =
                    dependenciesFinder.getDependencies(researchcatalogue.getResearchid());

            if(dependencies.isEmpty()) {
                /* Initially available researches */
                researchTree.getResearchTreeNode(getSubjectPosition(researchcatalogue.getResearchsubject())).
                        addResearchTreeNode(researchNodes[researchcatalogue.getResearchid()-1]);

            } else {
                /* Research tree node with some dependencies */

                Iterator<Researchdependencies> iter = dependencies.iterator();
                
                while(iter.hasNext()) {
                    ResearchdependenciesId dependencyId = iter.next().getId();
                    
                    researchNodes[dependencyId.getParentresearchid()].
                            addResearchTreeNode(researchNodes[dependencyId.getChildresearchid()]);
                }
            }
        }

    }

    public static String getSubject(int i) {
        return SpecializationsGenerator.subjectList[i];
    }

    public static int getSubjectPosition(String subject) {
        int i;
        for(i = 0;i < SpecializationsGenerator.subjectList.length;i++)
            if(SpecializationsGenerator.subjectList[i].equals(subject))
                break;
        return i;
    }

}
