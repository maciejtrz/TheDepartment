package ConnectionDataBase;
// Generated 15-Jun-2011 22:43:29 by Hibernate Tools 3.2.1.GA



/**
 * Auctionhistory generated by hbm2java
 */
public class Auctionhistory  implements java.io.Serializable {


     private AuctionhistoryId id;
     private long offertime;

    public Auctionhistory() {
    }

    public Auctionhistory(AuctionhistoryId id, long offertime) {
       this.id = id;
       this.offertime = offertime;
    }
   
    public AuctionhistoryId getId() {
        return this.id;
    }
    
    public void setId(AuctionhistoryId id) {
        this.id = id;
    }
    public long getOffertime() {
        return this.offertime;
    }
    
    public void setOffertime(long offertime) {
        this.offertime = offertime;
    }




}


