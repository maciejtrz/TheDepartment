package ConnectionDataBase;
// Generated 11-Jun-2011 13:31:16 by Hibernate Tools 3.2.1.GA



/**
 * Messagesystem generated by hbm2java
 */
public class Messagesystem  implements java.io.Serializable {


     private int msgnumber;
     private String senderid;
     private String receiverid;
     private String date;
     private String subcjet;
     private String msg;

    public Messagesystem() {
    }

	
    public Messagesystem(int msgnumber, String senderid, String receiverid, String date) {
        this.msgnumber = msgnumber;
        this.senderid = senderid;
        this.receiverid = receiverid;
        this.date = date;
    }
    public Messagesystem(int msgnumber, String senderid, String receiverid, String date, String subcjet, String msg) {
       this.msgnumber = msgnumber;
       this.senderid = senderid;
       this.receiverid = receiverid;
       this.date = date;
       this.subcjet = subcjet;
       this.msg = msg;
    }
   
    public int getMsgnumber() {
        return this.msgnumber;
    }
    
    public void setMsgnumber(int msgnumber) {
        this.msgnumber = msgnumber;
    }
    public String getSenderid() {
        return this.senderid;
    }
    
    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }
    public String getReceiverid() {
        return this.receiverid;
    }
    
    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }
    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    public String getSubcjet() {
        return this.subcjet;
    }
    
    public void setSubcjet(String subcjet) {
        this.subcjet = subcjet;
    }
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }




}


