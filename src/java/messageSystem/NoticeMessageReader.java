/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import ConnectionDataBase.Messagesystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author kp1209
 */
public class NoticeMessageReader extends MessageManager {

     public NoticeMessageReader() {
        super(MessageSingleton.NOTICE_BOARD_OFFER);
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


    public void setAcceptTradeOffer(TradeOffer acceptedTradeOffer) {
        acceptedTradeOffer.accept();
        offeredTrades.remove(acceptedTradeOffer);
    }

    public void setDeclineTradeOffer(TradeOffer declinedTradeOffer) {
        declinedTradeOffer.decline();
        offeredTrades.remove(declinedTradeOffer);
    }

}
