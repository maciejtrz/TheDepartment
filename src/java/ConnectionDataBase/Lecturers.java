package ConnectionDataBase;
// Generated 08-Jun-2011 10:04:00 by Hibernate Tools 3.2.1.GA



/**
 * Lecturers generated by hbm2java
 */
public class Lecturers  implements java.io.Serializable {


     private String lecturername;
     private int price;
     private boolean usable;

    public Lecturers() {
    }

    public Lecturers(String lecturername, int price, boolean usable) {
       this.lecturername = lecturername;
       this.price = price;
       this.usable = usable;
    }
   
    public String getLecturername() {
        return this.lecturername;
    }
    
    public void setLecturername(String lecturername) {
        this.lecturername = lecturername;
    }
    public int getPrice() {
        return this.price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    public boolean isUsable() {
        return this.usable;
    }
    
    public void setUsable(boolean usable) {
        this.usable = usable;
    }




}


