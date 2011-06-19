/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import ConnectionDataBase.MessageSystemHelper;
import resources.ResourcesType;
import ConnectionDataBase.Messagesystem;
import Connections.UserManager;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author kp1209
 */
public class TradeOffer extends Messagesystem implements Serializable, Comparable {

    public int compareTo(Object o) {
        TradeOffer tradeOffer = (TradeOffer) o;

        return getExpireDate().compareTo(tradeOffer.getExpireDate());
    }

    protected class ParsingPosition {

        private int position;
        private char[] message;

        ParsingPosition(String encodedMessage) {
            message = encodedMessage.toCharArray();
            position = 0;
        }

        public char getNextCharacter() {
            return message[position++];
        }

        public boolean isDigit() {
            return Character.isDigit(message[position]);
        }

        public void moveForward() {
            position++;
        }

        public boolean isNotFinished() {
            return position < message.length;
        }
    }

    private String tradeDescription;

    private int resourcesOfferedType;
    private int amountOffered;

    private int resourcesWantedType;
    private int amountWanted;

    private Date expireDate;

    public TradeOffer() {
        resourcesOfferedType = 0;
        amountOffered = 0;

        resourcesWantedType = 0;
        amountWanted = 0;

        expireDate = new Date();

    }

    public String getExpireDateText() {
        return expireDate.toString();
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date date) {
        expireDate = date;
    }

    public void setExpireDate(long date) {
        expireDate.setTime(date);
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

    public String getResourcesOfferedName() {
        return ResourcesType.getResourcesList()[getResourcesOfferedType()].getResourcesName();
    }

    public String getResourcesWantedName() {
        return ResourcesType.getResourcesList()[getResourcesWantedType()].getResourcesName();
    }

    /*
     * Message format:
     * resourcesOffer#=amount resourcesWanted#=amount
     * Subject
     * Text... ...
     */
    public void parse(String encodedMessage) {
        ParsingPosition parsingPosition = new ParsingPosition(encodedMessage);

        setResourcesOfferedType(getNumber(parsingPosition));
        setAmountOffered(getNumber(parsingPosition));
        setResourcesWantedType(getNumber(parsingPosition));
        setAmountWanted(getNumber(parsingPosition));
        setExpireDate(getLongNumber(parsingPosition));
        setTradeDescription(getTradeDesrciptionText(parsingPosition));
    }
    
    protected String getNumberString(ParsingPosition parsingPosition) {
        StringBuilder builder = new StringBuilder();

        while(parsingPosition.isDigit()) {
            builder.append(parsingPosition.getNextCharacter());
        }

        parsingPosition.moveForward();
        
        return builder.toString();
    }

    protected long getLongNumber(ParsingPosition parsingPosition) {

        return Long.parseLong(getNumberString(parsingPosition));
    }

    protected int getNumber(ParsingPosition parsingPosition) {

        return Integer.parseInt(getNumberString(parsingPosition));
    }

    protected String getTradeDesrciptionText(ParsingPosition parsingPosition) {
        StringBuilder builder = new StringBuilder();

        while(parsingPosition.isNotFinished()) {
            builder.append(parsingPosition.getNextCharacter());
        }

        return builder.toString();
    }

    public String encode() {
        return getResourcesOfferedType() + "=" + getAmountOffered() + " " +
                getResourcesWantedType() + "=" + getAmountWanted() + "\n" +
                getExpireDate().getTime() + "\n" +
                (getTradeDescription() == null || getTradeDescription().isEmpty()
                        ? "" : getTradeDescription());
    }

    public boolean accept() {

        boolean result = UserManager.makeTrade(this);

        if(result)
            finishOffer();

       return result;
    }

    public void decline() {
        finishOffer();
    }

    private void finishOffer() {
        MessageSystemHelper messageSystemHelper = new MessageSystemHelper();
        messageSystemHelper.deleteMsg(getMsgnumber());
    }

    public void cleanTradeOffer() {
        setAmountOffered(0);
            setAmountWanted(0);
            setExpireDate(new Date());
            setResourcesOfferedType(0);
            setResourcesWantedType(0);
            setSubject("");
            setTradeDescription("");
            setReceiverid("");
    }

    public void setTradeOffer(TradeOffer tradeOffer) {
        setAmountOffered(tradeOffer.getAmountOffered());
        setAmountWanted(tradeOffer.getAmountWanted());
        setExpireDate(tradeOffer.getExpireDate());
        setResourcesOfferedType(tradeOffer.getResourcesOfferedType());
        setResourcesWantedType(tradeOffer.getResourcesWantedType());
        setTradeDescription(tradeOffer.getTradeDescription());
        setSubject(tradeOffer.getSubject());
        setReceiverid(tradeOffer.getReceiverid());
        setSenderid(tradeOffer.getSenderid());
    }
    

}
