package tech.flapweb.apps.rest.entities;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT o FROM Task o"),
    @NamedQuery(name = "Task.findByOwner", query = "SELECT o FROM Task o WHERE o.owner = :owner")    
})
public class Task implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @NotNull( message="title cannot be empty" )
    @SafeHtml( message="invalid html markup")
    @Size(min = 1, max = 16, message = "Title must be 3-16 characters")
    private String title;
    
    @NotNull( message="details cannot be empty" )
    @SafeHtml( message="invalid html markup")
    @Size(min = 3, max = 256, message = "Details must be 3-256 characters")
    private String details;
    
    private Boolean completed = false;
    
    @NotNull( message="owner cannot be null" )
    @Size(min = 3, max = 30, message = "Owner must be 3-30 characters")
    private String owner;
    
    @CreationTimestamp
    private Instant created;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }
    
    public long getCreatedTimestamp(){
        return this.created.getEpochSecond();
    }
}