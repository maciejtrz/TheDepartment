/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author kp1209
 */
public class TradeOffer extends MessageManager {

    private static List<SelectItem> resources;

    private String subject;
    private String tradeDescription;

    private int resourcesOfferedType;
    private int amountOffered;

    private int resourcesWantedType;
    private int amountWanted;


    public TradeOffer() {
        super(MessageSingleton.TRADE_OFFER);
        resourcesOfferedType = 0;
        amountOffered = 0;

        resourcesWantedType = 0;
        amountWanted = 0;

        if(resources == null) {
            resources = new ArrayList<SelectItem>();
            for(int i = 0;i < ResourcesType.getResourcesList().length;i++) {
                resources.add(new SelectItem(new Integer(i),ResourcesType.getResourcesList()[i]));
            }
        }
    }

    public List<SelectItem> getAvailableResources() {
        return resources;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTradeDescription() {
        return tradeDescription;
    }

    public void setTradeDescription(String tradeDescription) {
        this.tradeDescription = tradeDescription;
    }

    public int getResourcesOfferedType() {
        return resourcesOfferedType;
    }

    public void setResourcesOfferedType(int resourcesOfferedType) {
        this.resourcesOfferedType = resourcesOfferedType;
    }

    public int getAmountOffered() {
        return amountOffered;
    }

    public void setAmountOffered(int amountOffered) {
        this.amountOffered = amountOffered;
    }

    public int getAmountWanted() {
        return amountWanted;
    }

    public void setAmountWanted(int amountWanted) {
        this.amountWanted = amountWanted;
    }

    public int getResourcesWantedType(){
        return resourcesWantedType;
    }

    public void setResourcesWantedType(int resourcesWantedType) {
        this.resourcesWantedType = resourcesWantedType;
    }

    /*
     * Message format:
     * resourcesOffer#=amount resourcesWanted#=amount
     * Subject
     * Text... ...
     */
    public void parse(String encodedMessage) {

        char[] message = encodedMessage.toCharArray();

        Integer value;
        Integer nextPosition = 0;

        setResourcesOfferedType(getNumber(nextPosition++,message));
        setAmountOffered(getNumber(nextPosition++,message));

        setResourcesWantedType(getNumber(nextPosition++,message));
        setAmountWanted(getNumber(nextPosition++,message));

        setSubject(getOneLine(nextPosition++,message));
        setTradeDescription(getTradeDesrciption(nextPosition++,message));
    }

    private int getNumber(Integer nextPosition, char[] encodedMessage) {

        StringBuilder builder = new StringBuilder();

        while(Character.isDigit(encodedMessage[nextPosition])) {
            builder.append(encodedMessage[nextPosition++]);
        }

        return Integer.parseInt(builder.toString());
    }

    public String getOneLine(Integer nextPosition, char[] encodedMessage) {
        StringBuilder builder = new StringBuilder();
        while(encodedMessage[nextPosition] != '\n')
            builder.append(encodedMessage[nextPosition++]);

        return builder.toString();
    }

    public String getTradeDesrciption(Integer nextPosition, char[] message) {
        StringBuilder builder = new StringBuilder();

        for( ; nextPosition < message.length; nextPosition++)
            builder.append(message[nextPosition]);

        return builder.toString();
    }

    public String encode() {
        return getResourcesOfferedType() + "=" + getAmountOffered() + " " +
                getResourcesWantedType() + "=" + getAmountWanted() + " " +
                getSubject() + "\n" + getText();
    }

}
