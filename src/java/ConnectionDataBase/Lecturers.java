package ConnectionDataBase;
// Generated 20-Jun-2011 18:34:54 by Hibernate Tools 3.2.1.GA



/**
 * Lecturers generated by hbm2java
 */
public class Lecturers  implements java.io.Serializable {


     private String lecturername;
     private Integer price;
     private Boolean usable;

    public Lecturers() {
    }

	
    public Lecturers(String lecturername) {
        this.lecturername = lecturername;
    }
    public Lecturers(String lecturername, Integer price, Boolean usable) {
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
    public Integer getPrice() {
        return this.price;
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Boolean getUsable() {
        return this.usable;
    }
    
    public void setUsable(Boolean usable) {
        this.usable = usable;
    }




}


