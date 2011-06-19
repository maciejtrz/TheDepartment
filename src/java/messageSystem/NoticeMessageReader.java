/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messageSystem;

import java.io.Serializable;
import java.util.List;
import utilities.BasicUtils;

/**
 *
 * @author kp1209
 */
public class NoticeMessageReader extends MessageWriter implements Serializable  {
    private TradeOffer selectedTradeOffer;

    public NoticeMessageReader() {
        super(MessageSingleton.NOTICE_BOARD, MessageSingleton.NOTICE_BOARD_OFFER);
    }
    public List<TradeOffer> getOfferedNoticeTrades() {
        return OffersSuperviser.getNoticeMonitor().getNoticeOffers();
    }

    public void setAnswerTradeOffer(TradeOffer answeredTradeOffer) {
        answeredTradeOffer.setReceiverid(BasicUtils.getUserName());
        OffersSuperviser.getNoticeMonitor().acceptNoticeOffer(answeredTradeOffer);
    }

    public void setSelectedNoticeOffer(TradeOffer selectedTradeOffer) {
        this.selectedTradeOffer = selectedTradeOffer;
    }

    public TradeOffer getSelectedNoticeOffer() {
        return selectedTradeOffer;
    }
}
