/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import org.primefaces.event.FlowEvent;

/**
 *
 * @author kp1209
 */
public class PlainMessageReader extends MessageReader {

    public PlainMessageReader() {
        super(MessageSingleton.PLAIN_MESSAGE);
    }

    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

    public void update() {
        
    }

}
