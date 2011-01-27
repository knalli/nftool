// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool;

import fhkoeln.edb.nftool.Exercise;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Exercise_Roo_Entity {
    
    declare @type: Exercise: @Entity;
    
    declare @type: Exercise: @Table(name = "EXERCISES");
    
    @PersistenceContext
    transient EntityManager Exercise.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Exercise.id;
    
    @Version
    @Column(name = "version")
    private Integer Exercise.version;
    
    public Long Exercise.getId() {
        return this.id;
    }
    
    public void Exercise.setId(Long id) {
        this.id = id;
    }
    
    public Integer Exercise.getVersion() {
        return this.version;
    }
    
    public void Exercise.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Exercise.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Exercise.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Exercise attached = Exercise.findExercise(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Exercise.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public Exercise Exercise.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Exercise merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Exercise.entityManager() {
        EntityManager em = new Exercise().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Exercise.countExercises() {
        return entityManager().createQuery("select count(o) from Exercise o", Long.class).getSingleResult();
    }
    
    public static List<Exercise> Exercise.findAllExercises() {
        return entityManager().createQuery("select o from Exercise o", Exercise.class).getResultList();
    }
    
    public static Exercise Exercise.findExercise(Long id) {
        if (id == null) return null;
        return entityManager().find(Exercise.class, id);
    }
    
    public static List<Exercise> Exercise.findExerciseEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from Exercise o", Exercise.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
