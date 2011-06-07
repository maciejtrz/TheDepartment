/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ResearchPoints;

import ConnectionDataBase.Research;

/**
 *
 * @author karol
 */
public interface PrerequsiteChain {

    public void addChain(PrerequsiteChain c);
    public boolean sendToChain(Research r);
    public PrerequsiteChain getChain();

}
