package ConnectionDataBase;
// Generated 08-Jun-2011 10:04:00 by Hibernate Tools 3.2.1.GA



/**
 * LecturersspecializationsId generated by hbm2java
 */
public class LecturersspecializationsId  implements java.io.Serializable {


     private String lecturername;
     private String specialization;

    public LecturersspecializationsId() {
    }

    public LecturersspecializationsId(String lecturername, String specialization) {
       this.lecturername = lecturername;
       this.specialization = specialization;
    }
   
    public String getLecturername() {
        return this.lecturername;
    }
    
    public void setLecturername(String lecturername) {
        this.lecturername = lecturername;
    }
    public String getSpecialization() {
        return this.specialization;
    }
    
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof LecturersspecializationsId) ) return false;
		 LecturersspecializationsId castOther = ( LecturersspecializationsId ) other; 
         
		 return ( (this.getLecturername()==castOther.getLecturername()) || ( this.getLecturername()!=null && castOther.getLecturername()!=null && this.getLecturername().equals(castOther.getLecturername()) ) )
 && ( (this.getSpecialization()==castOther.getSpecialization()) || ( this.getSpecialization()!=null && castOther.getSpecialization()!=null && this.getSpecialization().equals(castOther.getSpecialization()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLecturername() == null ? 0 : this.getLecturername().hashCode() );
         result = 37 * result + ( getSpecialization() == null ? 0 : this.getSpecialization().hashCode() );
         return result;
   }   


}


