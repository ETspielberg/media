package unidue.ub.media.analysis;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Collection {

    @Id
    private String name;

    private Timeline stock;

    private Timeline requests;

    private TimelineGroup loans;

    public Collection() {}

    public Collection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timeline getStock() {
        return stock;
    }

    public void setStock(Timeline stock) {
        this.stock = stock;
    }

    public Timeline getRequests() {
        return requests;
    }

    public void setRequests(Timeline requests) {
        this.requests = requests;
    }

    public TimelineGroup getLoans() {
        return loans;
    }

    public void setLoans(TimelineGroup loans) {
        this.loans = loans;
    }
}
