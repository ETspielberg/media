/**
 * 
 */
package unidue.ub.media.journals;

import com.fasterxml.jackson.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "identifier")
@Entity
public class Journalcollection implements Cloneable, Comparable<Journalcollection> {
	
	@Id
    private String identifier;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "TEXT")
	private String anchor;

	private String type;
	
	private int year;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "journalcollection")
	private List<Journal> journals;
	
	public Journalcollection(){
		description = "";
		anchor = "";
		identifier = anchor + String.valueOf(year);
		year = LocalDate.now().getYear();
		journals = new ArrayList<>();
	}

	public Journalcollection(String anchor, int year, String type) {
		this.anchor = anchor;
		this.year = year;
		this.description = "";
		this.type = type;
		identifier = anchor + String.valueOf(year);
		journals = new ArrayList<>();
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonIgnore
	public String getIssns() {
		String issns = "";
		List<Journal> journals = getJournals();
		for (Journal journal : getJournals()) {
			if (!issns.isEmpty())
				issns += ";";
			issns += journal.getIssns();
		}
		return issns;
	}

	public List<Journal> getJournals() {
		return journals;
	}

	public Journalcollection setJournals(List<Journal> journals) {
		this.journals = journals;
		return this;
	}

	public void addJournal(Journal journal) {
		this.journals.add(journal);
	}

	public String getDescription() {
		return description;
	}

	public String getAnchor() {
		return anchor;
	}
	
	public int getYear() {
		return year;
	}

	public Journalcollection setDescription(String description) {
		this.description = description;
		return this;
	}

	public Journalcollection setAnchor(String anchor) {
		this.anchor = anchor;
		identifier = anchor + String.valueOf(year);
		return this;
	}
	
	public Journalcollection setYear(int year) {
		this.year = year;
		identifier = anchor + String.valueOf(year);
		return this;
	}

	@JsonIgnore
	public List<String> getIssnsList() {
		String issns = getIssns();
	    if (issns.contains(";"))
	        issns = issns.replace(";", ",");
		List<String> issnsList = new ArrayList<>();
	    if (issns.contains(",")){
	        issnsList = Arrays.asList(issns.split(","));
	    } else
	        issnsList.add(issns);
	    return issnsList;
	}
	
	public Journalcollection clone() {
		Journalcollection clone = new Journalcollection();
		clone.setAnchor(anchor).setDescription(description).setJournals(journals).setYear(year);
	    return clone;
	}
	
    public int compareTo(Journalcollection other) {
        if (this.year > other.year)
            return 1;
        else
            return -1;
    }
}
