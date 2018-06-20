package unidue.ub.media.analysis;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EbsData {

    @Id
    private String id;
    private String title;
    private String subjectArea;
    private String isbn;
    private double price;
    private String doi;
    private int year;
    private long totalUsage;
    private double pricePerUsage;
    private boolean selected;
    private String ebsModel;

    EbsData() {
        id = "";
        title = "";
        subjectArea = "";
        isbn = "";
        doi = "";
        price = 0.0;
        year = 0;
        totalUsage = 0;
        pricePerUsage = 0.0;
        selected = false;
        ebsModel = "";
        id = ebsModel + "_" + isbn;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEbsModel() {
        return ebsModel;
    }

    public void setEbsModel(String ebsModel) {
        this.ebsModel = ebsModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(String subjectArea) {
        this.subjectArea = subjectArea;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getTotalUsage() {
        return totalUsage;
    }

    public void setTotalUsage(long totalUsage) {
        this.totalUsage = totalUsage;
    }

    public double getPricePerUsage() {
        return pricePerUsage;
    }

    public void setPricePerUsage(double pricePerUsage) {
        this.pricePerUsage = pricePerUsage;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
