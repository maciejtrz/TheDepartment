package ConnectionDataBase;
// Generated 09-Jun-2011 15:21:02 by Hibernate Tools 3.2.1.GA



/**
 * Capacity generated by hbm2java
 */
public class Capacity  implements java.io.Serializable {


     private String idname;
     private int studentscapacity;
     private int phdscapacity;
     private int professorscapacity;

    public Capacity() {
    }

    public Capacity(String idname, int studentscapacity, int phdscapacity, int professorscapacity) {
       this.idname = idname;
       this.studentscapacity = studentscapacity;
       this.phdscapacity = phdscapacity;
       this.professorscapacity = professorscapacity;
    }
   
    public String getIdname() {
        return this.idname;
    }
    
    public void setIdname(String idname) {
        this.idname = idname;
    }
    public int getStudentscapacity() {
        return this.studentscapacity;
    }
    
    public void setStudentscapacity(int studentscapacity) {
        this.studentscapacity = studentscapacity;
    }
    public int getPhdscapacity() {
        return this.phdscapacity;
    }
    
    public void setPhdscapacity(int phdscapacity) {
        this.phdscapacity = phdscapacity;
    }
    public int getProfessorscapacity() {
        return this.professorscapacity;
    }
    
    public void setProfessorscapacity(int professorscapacity) {
        this.professorscapacity = professorscapacity;
    }




}


