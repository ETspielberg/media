package unidue.ub.media.analysis;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class DeletionList {

    @Id
    String id;

    Eventanalysis[] eventanalyses;

    String name;

    String description;

    Comment[] comments;

    String[] users;

    DeletionList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Eventanalysis[] getEventanalyses() {
        return eventanalyses;
    }

    public void setEventanalyses(Eventanalysis[] eventanalyses) {
        this.eventanalyses = eventanalyses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }
}
