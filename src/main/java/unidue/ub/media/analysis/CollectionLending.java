package unidue.ub.media.analysis;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class CollectionLending {

    @Id
    private String name;

    private List<TimeAndCount> studentLoans;

    CollectionLending() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TimeAndCount> getUsage() {
        return studentLoans;
    }

    public void setStudentLoans(List<TimeAndCount> studentLoans) {
        this.studentLoans = studentLoans;
    }
}
