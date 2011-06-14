/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import java.io.Serializable;


/**
 *
 * @author kp1209
 */
public class NoticeMessageWriter extends TradeWriter implements Serializable  {
    
    

    public NoticeMessageWriter() {
        super(MessageSingleton.NOTICE_BOARD_OFFER);
    }

}
