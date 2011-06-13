/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import resources.ResourcesType;
import ConnectionDataBase.Messagesystem;

/**
 *
 * @author kp1209
 */
public class TradeOffer extends Messagesystem {

    private class ParsingPosition {

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


    public TradeOffer() {
        resourcesOfferedType = 0;
        amountOffered = 0;

        resourcesWantedType = 0;
        amountWanted = 0;

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
        setTradeDescription(getTradeDesrciptionText(parsingPosition));
    }

    private int getNumber(ParsingPosition parsingPosition) {

        StringBuilder builder = new StringBuilder();

        while(parsingPosition.isDigit()) {
            builder.append(parsingPosition.getNextCharacter());
        }

        parsingPosition.moveForward();
        return Integer.parseInt(builder.toString());
    }

    private String getTradeDesrciptionText(ParsingPosition parsingPosition) {
        StringBuilder builder = new StringBuilder();

        while(parsingPosition.isNotFinished()) {
            builder.append(parsingPosition.getNextCharacter());
        }

        return builder.toString();
    }

    public String encode() {
        return getResourcesOfferedType() + "=" + getAmountOffered() + " " +
                getResourcesWantedType() + "=" + getAmountWanted() + "\n" +
                (getTradeDescription() == null || getTradeDescription().isEmpty()
                        ? "" : getTradeDescription());
    }

    public void accept() {
        System.out.println("Accepting...");
        System.out.println("Sender: " + getSenderid());
        System.out.println("Receiver: " + getReceiverid());

    }

    public void decline() {
        System.out.println("Declining...");
    }


}
