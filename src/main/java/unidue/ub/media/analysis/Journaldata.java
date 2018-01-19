package unidue.ub.media.analysis;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Journaldata {

    @Id
    private String identifier;

    private String type;

    private double price;

    private double calculatedprice;

    private double snip;

    private double impactfactor;

    private double journalrank;

    private String anchor;

    private String[] orders;

    private int year;

    private String note;

    private String[] subjects;


    public Journaldata(String identifier, int year) {
        this.identifier = identifier;
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public Journaldata setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getAnchor() {
        return anchor;
    }

    public Journaldata setAnchor(String anchor) {
        this.anchor = anchor;
        return this;
    }

    public String[] getOrders() {
        return orders;
    }

    public Journaldata setOrders(String[] orders) {
        this.orders = orders;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Journaldata setNote(String note) {
        this.note = note;
        return this;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Journaldata setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public String getType() {
        return type;
    }

    public Journaldata setType(String type) {
        this.type = type;
        return this;
    }

    public double getSnip() {
        return snip;
    }

    public Journaldata setSnip(double snip) {
        this.snip = snip;
        return this;
    }

    public double getJournalrank() {
        return journalrank;
    }

    public void setJournalrank(double journalrank) {
        this.journalrank = journalrank;
    }

    public int getYear() {
        return year;
    }

    public Journaldata setYear(int year) {
        this.year = year;
        return this;
    }

    public double getImpactfactor() {
        return impactfactor;
    }

    public Journaldata setImpactfactor(double impactfactor) {
        this.impactfactor = impactfactor;
        return this;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public Journaldata setSubjects(String[] subjects) {
        this.subjects = subjects;
        return this;
    }

    public double getCalculatedprice() {
        return calculatedprice;
    }

    public void setCalculatedprice(double calculatedprice) {
        this.calculatedprice = calculatedprice;
    }
}
