/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import java.io.Serializable;
import org.icefaces.application.PushRenderer;
import utilities.BasicUtils;

/**
 *
 * @author kp1209
 */
public class PlainMessageReader extends MessageWriter implements Serializable  {



    public PlainMessageReader() {
        super(MessageSingleton.PLAIN_MESSAGE);

        PushRenderer.addCurrentSession(BasicUtils.getUserName());
    }

    

}
