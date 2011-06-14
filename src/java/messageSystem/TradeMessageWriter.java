/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import java.io.Serializable;

public class TradeMessageWriter extends TradeWriter implements Serializable {

    /** Creates a new instance of TradeMessageWriter */
    public TradeMessageWriter() {
        super(MessageSingleton.TRADE_OFFER);
    }

    public TradeMessageWriter(int messageType) {
        super(messageType);
    }

    /* reading offers and accepting/decling... */

}
