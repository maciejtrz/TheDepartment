package ConnectionDataBase;
// Generated 14-Jun-2011 16:43:10 by Hibernate Tools 3.2.1.GA



/**
 * Messagesystem generated by hbm2java
 */
public class Messagesystem  implements java.io.Serializable {


     private int msgnumber;
     private String senderid;
     private String receiverid;
     private long creationtime;
     private String subject;
     private String msg;
     private boolean read;
     private int type;

    public Messagesystem() {
    }

	
    public Messagesystem(int msgnumber, String senderid, String receiverid, long creationtime, boolean read, int type) {
        this.msgnumber = msgnumber;
        this.senderid = senderid;
        this.receiverid = receiverid;
        this.creationtime = creationtime;
        this.read = read;
        this.type = type;
    }
    public Messagesystem(int msgnumber, String senderid, String receiverid, long creationtime, String subject, String msg, boolean read, int type) {
       this.msgnumber = msgnumber;
       this.senderid = senderid;
       this.receiverid = receiverid;
       this.creationtime = creationtime;
       this.subject = subject;
       this.msg = msg;
       this.read = read;
       this.type = type;
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
    public long getCreationtime() {
        return this.creationtime;
    }
    
    public void setCreationtime(long creationtime) {
        this.creationtime = creationtime;
    }
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public boolean isRead() {
        return this.read;
    }
    
    public void setRead(boolean read) {
        this.read = read;
    }
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }




}


