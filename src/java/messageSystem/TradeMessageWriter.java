/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import ConnectionDataBase.MessageSystemHelper;


public class TradeMessageWriter extends TradeOffer{

    /** Creates a new instance of TradeMessageWriter */
    public TradeMessageWriter() {
    }

    /* reading offers and accepting/decling... */

    public void sendTrade() {

        MessageSystemHelper messageSystemHelper = new MessageSystemHelper();
        messageSystemHelper.createMessage(getSender(),getSelectedReceiverId(),
                getSubject(),encode(),getMessageType());
        
    }

}
