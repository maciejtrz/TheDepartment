/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import java.io.Serializable;


/**
 *
 * @author root
 */
public class VikreyAuction extends Auction implements Serializable {

    /** Creates a new instance of VikreyAction */
    public VikreyAuction() {
        super(AuctionFactory.VIKREY_AUCTION,true,true);
    }

    private static final String typeName = "Vikrey";

    @Override
    public String getTypeName() {
       return typeName;
    }

}
