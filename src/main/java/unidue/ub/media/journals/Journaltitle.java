/**
 * 
 */
package unidue.ub.media.journals;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Plain Old Java Object as representation of a journal title consisting of a single ISSN.
 * @author Eike Spielberg
 *
 */
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "identifier")
@Entity
public class Journaltitle implements Cloneable {
	
	@Column(columnDefinition = "TEXT")
	private String name;

	@Id
	private String issn;
	
	private String type;

	@ManyToOne
	@JoinColumn(name="journal_zdbid")
	private Journal journal;

	@ManyToMany
	@JoinColumn(name="journalcollection_identifier")
	private List<Journalcollection> journalcollections;
	
	public Journaltitle() {
		name = "";
		issn = "";
		type="electronic";
	}

	public Journaltitle(String issn, Journal journal, String type, String name) {
		this.issn = issn;
		this.journal = journal;
		this.type = type;
		this.name = name;
		this.journalcollections = new ArrayList<>();
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	public Journal getJournal() {
		return journal;
	}

	/**
	 * returns the name of the journal title
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * returns the ISSN of the journal title
	 * @return the issn
	 */
	public String getIssn() {
		return issn;
	}

	/**
	 * returns the type to the journal (print or online)
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * sets the name of the journal title
	 * @param name the name to be set
	 * @return the updated object
	 */
	public Journaltitle setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * sets the ISSN of the journal title
	 * @param issn the issn to be set
	 * @return the updated object
	 */
	public Journaltitle setIssn(String issn) {
		this.issn = issn;
		return this;
	}

	/**
	 * sets the type of the journal title
	 * @param type the type (print or online)
	 * @return the updated object
	 * 	 */
	public Journaltitle setType(String type) {
		this.type = type;
		return this;
	}

    /**
     * returns a new <code>Journaltitle</code> object as clone of an existing one
     * 
     * @return a clone of the original journal title
     */
    public Journaltitle clone() {
    	Journaltitle clone = new Journaltitle();
    	clone.setIssn(issn).setName(name).setType(type).setJournalcollections(journalcollections);
    	return clone;
    }

	public List<Journalcollection> getJournalcollections() {
		return journalcollections;
	}

	public Journaltitle setJournalcollections(List<Journalcollection> journalcollections) {
		this.journalcollections = journalcollections;
		return this;
	}

	public Journaltitle addJournalCollection(Journalcollection journalcollection) {
    	this.journalcollections.add(journalcollection);
    	return this;
	}
}
