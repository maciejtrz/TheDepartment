package ConnectionDataBase;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ResearchdependenciesHelper extends AbstractHelper {
    
    public List<Researchdependencies> getDependencies(Integer child) {
        
        Session session = createNewSessionAndTransaction();
        Query q = (Query) session.createQuery("from Researchdependencies where ChildResearchId=ʼ" +
                child + "ʼ");
        
        return (List<Researchdependencies>) q.list();
    }    

}
