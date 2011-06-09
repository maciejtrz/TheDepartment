package ConnectionDataBase;
// Generated 09-Jun-2011 22:33:22 by Hibernate Tools 3.2.1.GA



/**
 * ResearchdependenciesId generated by hbm2java
 */
public class ResearchdependenciesId  implements java.io.Serializable {


     private int parentresearchid;
     private int childresearchid;

    public ResearchdependenciesId() {
    }

    public ResearchdependenciesId(int parentresearchid, int childresearchid) {
       this.parentresearchid = parentresearchid;
       this.childresearchid = childresearchid;
    }
   
    public int getParentresearchid() {
        return this.parentresearchid;
    }
    
    public void setParentresearchid(int parentresearchid) {
        this.parentresearchid = parentresearchid;
    }
    public int getChildresearchid() {
        return this.childresearchid;
    }
    
    public void setChildresearchid(int childresearchid) {
        this.childresearchid = childresearchid;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ResearchdependenciesId) ) return false;
		 ResearchdependenciesId castOther = ( ResearchdependenciesId ) other; 
         
		 return (this.getParentresearchid()==castOther.getParentresearchid())
 && (this.getChildresearchid()==castOther.getChildresearchid());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getParentresearchid();
         result = 37 * result + this.getChildresearchid();
         return result;
   }   


}


