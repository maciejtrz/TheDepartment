package ConnectionDataBase;
// Generated 08-Jun-2011 10:04:00 by Hibernate Tools 3.2.1.GA



/**
 * Departmentinfo generated by hbm2java
 */
public class Departmentinfo  implements java.io.Serializable {


     private String idname;
     private String name;

    public Departmentinfo() {
    }

	
    public Departmentinfo(String idname) {
        this.idname = idname;
    }
    public Departmentinfo(String idname, String name) {
       this.idname = idname;
       this.name = name;
    }
   
    public String getIdname() {
        return this.idname;
    }
    
    public void setIdname(String idname) {
        this.idname = idname;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }




}


