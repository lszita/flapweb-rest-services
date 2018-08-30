package tech.flapweb.apps.rest.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT o FROM Task o")
})
public class Task implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull( message="title cannot be empty" )
    @SafeHtml( message="invalid html markup")
    private String title;
    @NotNull( message="details cannot be empty" )
    @SafeHtml( message="invalid html markup")
    private String details;
    private Boolean completed = false;
    @NotNull( message="owner id cannot be null" )
    private Long ownerId;

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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
