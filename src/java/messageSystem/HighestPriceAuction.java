/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import java.util.HashSet;
import java.util.Set;
import utilities.BasicUtils;


/**
 *
 * @author root
 */
public class HighestPriceAuction extends AuctionMessageWriter{

    private Set<String> bidders = new HashSet<String>();


    @Override
    public void setHighestOfferedPrice(String price) {
        bidders.add(BasicUtils.getUserName());
        super.setHighestOfferedPrice(price);
    }



}
