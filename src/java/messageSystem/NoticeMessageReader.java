/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import ConnectionDataBase.Messagesystem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utilities.BasicUtils;

/**
 *
 * @author kp1209
 */
public class NoticeMessageReader extends MessageManager {

    public NoticeMessageReader() {
        super(MessageSingleton.NOTICE_BOARD,MessageSingleton.NOTICE_BOARD_OFFER);
    }

    public List<TradeOffer> offeredNoticeTrades;

    public List<TradeOffer> getOfferedNoticeTrades() {
        offeredNoticeTrades = new ArrayList<TradeOffer>();

        List<Messagesystem> encodedTrades = getMessages();
        Iterator<Messagesystem> iterator = encodedTrades.iterator();

        System.out.println("Adding new messages from DB...");
        System.out.println("Number of notice boards: " + encodedTrades.size());

        while(iterator.hasNext()) {
            Messagesystem message = iterator.next();

            TradeOffer tradeOffer  = new TradeOffer();

            tradeOffer.parse(message.getMsg());
            tradeOffer.setSenderid(message.getSenderid());
            tradeOffer.setDate(message.getDate());
            tradeOffer.setMsgnumber(message.getMsgnumber());

            offeredNoticeTrades.add(tradeOffer);
        }

        return offeredNoticeTrades;
    }


    public void setAnswerTradeOffer(TradeOffer answeredTradeOffer) {
        System.out.println("Answering trade offer...");
        answeredTradeOffer.setReceiverid(BasicUtils.getUserName());
        if(answeredTradeOffer.accept()) {
             offeredNoticeTrades.remove(answeredTradeOffer);
        }

    }

}
