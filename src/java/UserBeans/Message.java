/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UserBeans;

import ConnectionDataBase.MessageSystemHelper;
import Connections.ConnectionSingleton;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author pk2109
 */
public class Message {

   // public String sender;
    public String receiver;
    public String subject;
    public String text;


    /** Creates a new instance of Message */
    public Message() {
    }
/*
    public void setSender(String sender){
        this.sender=sender;
    }

    public String getSender(){
        return this.sender;
    }
    */

    public void setReceiver(String receiver){
        this.receiver=receiver;
    }

    public String getReceiver(){
        return this.receiver;
    }

    public void setSubject(String subject){
        this.subject=subject;
    }

    public String getSubject(){
        return this.subject;
    }

    public void setText(String text){
        this.text=text;
    }

    public String getText(){
        return this.text;
    }

    public String send(){

        String name = utilities.BasicUtils.getUserName();

        MessageSystemHelper msghelp = new MessageSystemHelper();
        System.out.println(name);
        System.out.println(getReceiver());
        System.out.println(getSubject());
        System.out.println(getText());
        msghelp.createMsg("kar","piotrus", "asd ", "kurski huj");

        return "success";
    }
}
