/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

/**
 *
 * @author kp1209
 */
public class MessageSingleton {

    /* Plain message from user to user */
    public static final int PLAIN_MESSAGE = 0;
    public static final int TRADE_OFFER = 1;
    public static final int NOTICE_BOARD_OFFER = 2;
    public static final int AUCTION_OFFER = 3;


    public static final String NOTICE_BOARD = "NoticeBoard";


    private MessageSingleton() {

    }
}
