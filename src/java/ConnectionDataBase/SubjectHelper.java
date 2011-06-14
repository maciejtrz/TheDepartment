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
 * @author karol
 */
public class SubjectHelper extends AbstractHelper {

    public List<Subject> getSubjectList() {


       Session session = createNewSessionAndTransaction();
        Query q = (Query) session.createQuery("from Subject");
        List<Subject> list = q.list();
        session.close();
        return list;
    }
    
}
