package ConnectionDataBase;
// Generated 08-Jun-2011 16:56:09 by Hibernate Tools 3.2.1.GA



/**
 * Buildings generated by hbm2java
 */
public class Buildings  implements java.io.Serializable {


     private String idname;
     private int tresco;
     private int macchicken;
     private int docpub;
     private int studentunion;
     private int lectureroom;
     private int phdsoffice;
     private int professorsoffice;
     private int labolatories;
     private int brain;

    public Buildings() {
    }

    public Buildings(String idname, int tresco, int macchicken, int docpub, int studentunion, int lectureroom, int phdsoffice, int professorsoffice, int labolatories, int brain) {
       this.idname = idname;
       this.tresco = tresco;
       this.macchicken = macchicken;
       this.docpub = docpub;
       this.studentunion = studentunion;
       this.lectureroom = lectureroom;
       this.phdsoffice = phdsoffice;
       this.professorsoffice = professorsoffice;
       this.labolatories = labolatories;
       this.brain = brain;
    }
   
    public String getIdname() {
        return this.idname;
    }
    
    public void setIdname(String idname) {
        this.idname = idname;
    }
    public int getTresco() {
        return this.tresco;
    }
    
    public void setTresco(int tresco) {
        this.tresco = tresco;
    }
    public int getMacchicken() {
        return this.macchicken;
    }
    
    public void setMacchicken(int macchicken) {
        this.macchicken = macchicken;
    }
    public int getDocpub() {
        return this.docpub;
    }
    
    public void setDocpub(int docpub) {
        this.docpub = docpub;
    }
    public int getStudentunion() {
        return this.studentunion;
    }
    
    public void setStudentunion(int studentunion) {
        this.studentunion = studentunion;
    }
    public int getLectureroom() {
        return this.lectureroom;
    }
    
    public void setLectureroom(int lectureroom) {
        this.lectureroom = lectureroom;
    }
    public int getPhdsoffice() {
        return this.phdsoffice;
    }
    
    public void setPhdsoffice(int phdsoffice) {
        this.phdsoffice = phdsoffice;
    }
    public int getProfessorsoffice() {
        return this.professorsoffice;
    }
    
    public void setProfessorsoffice(int professorsoffice) {
        this.professorsoffice = professorsoffice;
    }
    public int getLabolatories() {
        return this.labolatories;
    }
    
    public void setLabolatories(int labolatories) {
        this.labolatories = labolatories;
    }
    public int getBrain() {
        return this.brain;
    }
    
    public void setBrain(int brain) {
        this.brain = brain;
    }




}

