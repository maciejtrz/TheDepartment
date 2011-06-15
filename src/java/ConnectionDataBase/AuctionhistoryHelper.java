/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ConnectionDataBase;


import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author root
 */
public class AuctionhistoryHelper extends AbstractHelper {

    public void addAuctionOffer(Auctionhistory auctionOffer) {
        Session session = createNewSessionAndTransaction();
        session.saveOrUpdate(auctionOffer);
        commitTransaction(session);
    }

    public int getHighestAuctionOffer(int auctionNumber) {
        Session session = createNewSessionAndTransaction();

        String query = "from Auctionhistory where "
                + "auctionId = " + auctionNumber + " order by offer desc";

        System.out.println("Query for db: " + query);

        Query q = session.createQuery(query);


        List<Auctionhistory> auctionHistoryOffer =
                (List<Auctionhistory>) q.list();

        session.close();

        int result;
        if(auctionHistoryOffer == null || auctionHistoryOffer.isEmpty() )
            result = 0;
        else {
            result = auctionHistoryOffer.get(0).getId().getOffer();
        }
        return result;
    }

    public void deleteOffers(int auctionNumber) {
        Session session = createNewSessionAndTransaction();

        Query q = session.createQuery("from Auctionhistory where "
                + "auctionId = " + auctionNumber + " ORDER BY offer");

        Iterator<Auctionhistory> iterator = q.list().iterator();
        while(iterator.hasNext()) {
            Auctionhistory auctionOffer = iterator.next();
            session.delete(auctionOffer);
        }

        commitTransaction(session);
    }

}
