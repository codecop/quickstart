package ${query.resolve("node.package.name")};

import javax.persistence.Entity;
import javax.persistence.Id;
import play.db.jpa.JPA;

/**
 * ${query.resolve("type.name")} entity managed by JPA.
 */
@Entity
public class ${query.resolve("type.name")}{
 
   @Id 
   private Long id;
 
    /**
    * Find a ${query.resolve("type.name")} by id.
    */
    public static ${query.resolve("type.name")} findById(Long id){
      return JPA.em().find(${query.resolve("type.name")}.class, id);
    }
  
    /**
    * Delete this ${query.resolve("type.name")}.
    */
    public void delete() {        
        JPA.em().remove(this);    
    }
    
    /**
    * Insert this new ${query.resolve("type.name")}.
    */
    public void save(){
        JPA.em().persist(this);
    }
 
 }
 