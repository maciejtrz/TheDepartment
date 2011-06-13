/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import ConnectionDataBase.Messagesystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TradeMessageReader extends TradeOffer {
    
    public TradeMessageReader() {
    }

    public List<TradeOffer> offeredTrades;

    public void sendOffer() {       
    }

    public List<TradeOffer> getOfferedTrades() {
        offeredTrades = new ArrayList<TradeOffer>();

        List<Messagesystem> encodedTrades = getMessages();
        Iterator<Messagesystem> iterator = encodedTrades.iterator();

        while(iterator.hasNext()) {
            Messagesystem message = iterator.next();

            TradeOffer tradeOffer  = new TradeOffer();

            System.out.println("Parsing: " + message.getMsg());

            tradeOffer.parse(message.getMsg());
            tradeOffer.setSender(message.getSenderid());
            tradeOffer.setReceiver(message.getReceiverid());
            tradeOffer.setDate(message.getDate());
            tradeOffer.setUniqueid(message.getMsgnumber());

            offeredTrades.add(tradeOffer);
        }

        return offeredTrades;
    }

}
