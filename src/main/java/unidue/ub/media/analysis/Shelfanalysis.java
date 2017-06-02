/**
 * 
 */
package unidue.ub.media.analysis;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Plain old java object holding a shelf analysis of one year and one document. 
 * @author Eike Spielberg
 * @version 1
 */
@Entity
public class Shelfanalysis {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String collection;
    
    private String room;
    
    private String subLibrary;
    
    private String shelf;
    
    private double relativeLoan;
    
    private String yearRange;
    
    public Shelfanalysis() {
    	collection = "";
    	room = "";
    	shelf = "";
    	relativeLoan = 0.0;
    	yearRange = "";
    }

    /**
     * returns the collection
     * @return the collection
     */
    public String getCollection() {
        return collection;
    }

    /**
     * returns the room
     * @return the room
     */
    public String getRoom() {
        return room;
    }

    /**
     * returns the shelf
     * @return the shelf
     */
    public String getShelf() {
        return shelf;
    }

    /**
     * returns the relative loan
     * @return the relativeLoan
     */
    public double getRelativeLoan() {
        return relativeLoan;
    }
    
    /**
     * returns the year range
     * @return the year range
     */
    public String getYearRange() {
        return yearRange;
    }
    
    /**
     * returns the sub library
     * @return the sub library
     */
    public String getSubLibrary() {
        return subLibrary;
    }


    /**
     * sets the collection
     * @param collection the collection to be set
     * @return ShelfAnalysis the updated object
     */
    public Shelfanalysis setCollection(String collection) {
        this.collection = collection;
        return this;
    }

    /**
     * sets the room
     * @param room the room to be set
     * @return ShelfAnalysis the updated object
     */
    public Shelfanalysis setRoom(String room) {
        this.room = room;
        return this;
    }

    /**
     * sets the shelf
     * @param shelf the shelf to be set
     * @return ShelfAnalysis the updated object
     */
    public Shelfanalysis setShelf(String shelf) {
        this.shelf = shelf;
        return this;
    }

    /**
     * sets the relative loan
     * @param relativeLoan the relativeLoan to be set
     * @return ShelfAnalysis the updated object
     */
    public Shelfanalysis setRelativeLoan(double relativeLoan) {
        this.relativeLoan = relativeLoan;
        return this;
    }
    
    /**
     * sets the year range
     * @param yearRange the year range to be set
     * @return ShelfAnalysis the updated object
     */
    public Shelfanalysis setYearRange(String yearRange) {
        this.yearRange = yearRange;
        return this;
    }
    
    /**
     * sets the sub library
     * @param subLibrary the sub library to be set
     * @return ShelfAnalysis the updated object
     */
    public Shelfanalysis setSubLibrary(String subLibrary) {
        this.subLibrary = subLibrary;
        return this;
    }
}
