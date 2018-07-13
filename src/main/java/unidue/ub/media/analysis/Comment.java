package unidue.ub.media.analysis;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class Comment {

    @Id
    private String id;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date creationDate;

    private String user;

    private String[] sharedWith;

    @Column(columnDefinition = "TEXT")
    private String contents;

    private boolean isResolved;

    Comment() {
    }

    public String[] getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(String[] sharedWith) {
        this.sharedWith = sharedWith;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }
}
