/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ConnectionDataBase;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author kp1209
 */
public class ResearchcatalogueHelper extends AbstractHelper {

    public List<Researchcatalogue> getResearchCatalogue() {

        Session session = createNewSessionAndTransaction();
        Query q = (Query) session.createQuery("from Researchcatalogue");
        return (List<Researchcatalogue>) q.list();

    }



}
