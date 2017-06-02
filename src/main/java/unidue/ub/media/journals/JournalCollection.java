/**
 * 
 */
package unidue.ub.media.journals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Plain old java object as representation of a journal collection, collecting all <code>JournalTitles</code> (by their issn) contained in this collection
 * @author Eike Spielberg
 * @version 1
 */
@Entity
public class JournalCollection implements Cloneable, Comparable<JournalCollection> {
	
	@Id
    @GeneratedValue
    private long id;
	
	@Column(columnDefinition = "TEXT")
	private String issns;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "TEXT")
	private String anchor;
	
	private Double price;
	
	private int year;
	
	/**
	 * general constructor and initialization
	 */
	public JournalCollection(){
		issns = "";
		description = "";
		anchor = "";
		price = 0.0;
		year = LocalDate.now().getYear();
	}

	/**
	 * returns the ISSNs contained in the collection
	 * @return the issns
	 */
	public String getIssns() {
		return issns;
	}

	/**
	 * returns the description of the the collection
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * returns the anchor of the collection
	 * @return the anchor
	 */
	public String getAnchor() {
		return anchor;
	}

	/**
	 * returns the price of the collection
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	
	/**
	 * returns the year of the collection
	 * @return the zdbID
	 */
	public int getYear() {
		return year;
	}

	/**
	 * sets the issns contained in the collection
	 * @param issns the issns contained in the collection
	 * @return JournalCollection the updated object
	 */
	public JournalCollection setIssns(String issns) {
		this.issns = issns;
		return this;
	}

	/**
	 * sets the description of the collection
	 * @param description the description of the collection
	 * @return JournalCollection the updated object
	 */
	public JournalCollection setDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * sets the anchor of the collection
	 * @param anchor the anchor of the collection
	 * @return JournalCollection the updated object
	 */
	public JournalCollection setAnchor(String anchor) {
		this.anchor = anchor;
		return this;
	}

	/**
	 * sets the price of the collection
	 * @param price the price of the collection
	 * @return JournalCollection the updated object
	 */
	public JournalCollection setPrice(Double price) {
		this.price = price;
		return this;
	}
	
	/**
	 * sets the year of the collection
	 * @param year the year of the collection
	 * @return JournalCollection the updated object
	 */
	public JournalCollection setYear(int year) {
		this.year = year;
		return this;
	}
	
	/**
	 * adds an issn to the string of issns (separated by ,) contained in the collection
	 * @param issn the issn 
	 */
	public void addISSN(String issn) {
	    if (issns.isEmpty())
	        issns = issn;
	    else
	        issns = issns + "," + issn;
	}
	
	/**
	 * adds an journal price to the collection price
	 * @param priceJournal the price of the individual journal 
	 */
	public void addPrice(double priceJournal) {
		price = price + priceJournal;
	}

	/**
	 * returns the issns contained in the collection as list.
	 * @return issnsList the list of issns
	 */
	public List<String> getIssnsList() {
	    if (issns.contains(";"))
	        issns = issns.replace(";", ",");
		List<String> issnsList = new ArrayList<>();
	    if (issns.contains(",")){
	        issnsList = Arrays.asList(issns.split(","));
	    } else
	        issnsList.add(issns);
	    return issnsList;
	}
	
	/**
	 * clones the <code>JournalCollection</code> and returns an identical <code>JournalCollection</code> object
	 * @return clone a copy of the journal collection
	 */
	public JournalCollection clone() {
		JournalCollection clone = new JournalCollection();
		clone.setAnchor(anchor).setDescription(description).setIssns(issns).setPrice(price).setYear(year);
	    return clone;
	}
	
	/**
     * allows for a comparison of two collections with respect to their years.
     * Allows for the ordering of collections according to the years.
     *
     * @return difference +1 of event is after the other one, -1 if it before.
     */
    public int compareTo(JournalCollection other) {
        if (this.year > other.year)
            return 1;
        else
            return -1;
    }
}
