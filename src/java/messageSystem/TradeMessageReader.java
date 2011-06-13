/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import ConnectionDataBase.Messagesystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TradeMessageReader extends MessageManager {
    
    public TradeMessageReader() {
        super(MessageSingleton.TRADE_OFFER);
    }

    public List<TradeOffer> offeredTrades;

    public List<TradeOffer> getOfferedTrades() {
        offeredTrades = new ArrayList<TradeOffer>();

        List<Messagesystem> encodedTrades = getMessages();
        Iterator<Messagesystem> iterator = encodedTrades.iterator();

        while(iterator.hasNext()) {
            Messagesystem message = iterator.next();

            TradeOffer tradeOffer  = new TradeOffer();

            tradeOffer.parse(message.getMsg());
            tradeOffer.setSenderid(message.getSenderid());
            tradeOffer.setReceiverid(message.getReceiverid());
            tradeOffer.setDate(message.getDate());
            tradeOffer.setMsgnumber(message.getMsgnumber());

            offeredTrades.add(tradeOffer);
        }

        return offeredTrades;
    }

}
