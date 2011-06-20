package messageSystem;

import ConnectionDataBase.MessageSystemHelper;
import ConnectionDataBase.Messagesystem;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import org.icefaces.application.PushRenderer;


public class NoticeMonitor {

    private PriorityQueue<TradeOffer> currentNotices = new PriorityQueue<TradeOffer>();
    private List<TradeOffer> listNotices = new ArrayList<TradeOffer>();
    private MessageSystemHelper messageSystemHelper = new MessageSystemHelper();

    public final static String noticeGroup = "noticeGroup";

    public NoticeMonitor() {
        List<Messagesystem> noticesDb =
                messageSystemHelper.getMessages(MessageSingleton.NOTICE_BOARD, MessageSingleton.NOTICE_BOARD_OFFER);

        Set<TradeOffer> noticesToRemove = new HashSet<TradeOffer>();
        Date currentDate = new Date();

        Iterator<Messagesystem> iterator = noticesDb.iterator();

       while(iterator.hasNext()) {
           Messagesystem noticeOffer = iterator.next();

           TradeOffer tradeOffer = new TradeOffer();
           tradeOffer.parse(noticeOffer.getMsg());
           tradeOffer.setMsgnumber(noticeOffer.getMsgnumber());

           if(tradeOffer.getExpireDate().before(currentDate)) {
                noticesToRemove.add(tradeOffer);
           } else {
           tradeOffer.setSenderid(noticeOffer.getSenderid());
           tradeOffer.setSubject(noticeOffer.getSubject());
           tradeOffer.setCreationtime(noticeOffer.getCreationtime());
           tradeOffer.setMsgnumber(noticeOffer.getMsgnumber());

           currentNotices.add(tradeOffer);
           listNotices.add(tradeOffer);
           }
       }

       Iterator<TradeOffer> noticeIterator = noticesToRemove.iterator();

        while (noticeIterator.hasNext()) {
            TradeOffer noticeOffer = noticeIterator.next();
            messageSystemHelper.deleteMsg(noticeOffer.getMsgnumber());
        }
       
    }

    public synchronized void addNoticeOffer(TradeOffer noticeOffer) {

        TradeOffer tradeOffer = new TradeOffer();
        tradeOffer.setTradeOffer(noticeOffer);

        currentNotices.add(tradeOffer);
        listNotices.add(tradeOffer);

        System.out.println("Adding notice offer...");
        System.out.println("Amount offered: " + noticeOffer.getAmountOffered());
        System.out.println("Amount wanted: " + noticeOffer.getAmountWanted());

        int msgNumber = messageSystemHelper.createMessage(noticeOffer.getSenderid(),
                MessageSingleton.NOTICE_BOARD,
                noticeOffer.getSubject(),noticeOffer.encode(), 
                MessageSingleton.NOTICE_BOARD_OFFER);

        tradeOffer.setMsgnumber(msgNumber);

        PushRenderer.render(NoticeMonitor.noticeGroup);
    }

    public synchronized void update() {
        Date currentDate = new Date();

        while(!currentNotices.isEmpty()) {
            
            TradeOffer noticeOffer = currentNotices.peek();
            if(noticeOffer.getExpireDate().before(currentDate)) {
                currentNotices.poll();
                listNotices.remove(noticeOffer);

                messageSystemHelper.deleteMsg(noticeOffer.getMsgnumber());
            } else {
                return;
            }
        }
    }

    public synchronized List<TradeOffer> getNoticeOffers() {
        
        return listNotices;
    }

    public boolean acceptNoticeOffer(TradeOffer noticeOffer) {

        if(!currentNotices.contains(noticeOffer))
            return false;
        
        boolean result = noticeOffer.accept();

        if(result) {
            currentNotices.remove(noticeOffer);
            listNotices.remove(noticeOffer);
        }

        return result;
    }

}
