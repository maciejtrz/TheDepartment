/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

public class TradeMessageWriter extends TradeWriter {

    /** Creates a new instance of TradeMessageWriter */
    public TradeMessageWriter() {
        super(MessageSingleton.TRADE_OFFER);
    }

    public TradeMessageWriter(int messageType) {
        super(messageType);
    }

    /* reading offers and accepting/decling... */

}
