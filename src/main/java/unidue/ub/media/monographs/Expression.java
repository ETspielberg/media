package unidue.ub.media.monographs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Representation of one expression consisting of different manifestations
 * 
 * @author Eike Spielberg
 * @version 1
 */
@Entity
public class Expression implements Cloneable {

	@Id
	private String id;

	private String shelfmarkBase;
	
	private BibliographicInformation bibliographicInformation;

	private List<Manifestation> documents = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * creates a new <code>Work</code>-object with the given basic shelfmark
	 *
	 * @param shelfmarkBase
	 *            the basic shelfmark of the work
	 */

	public Expression(String shelfmarkBase) {
		this.shelfmarkBase = shelfmarkBase;
	}

	/**
	 * returns the basic shelfmark for this work
	 *
	 * @return shelfmarkBase the basic shelfmark
	 */
	public String getShelfmarkBase() {
		return shelfmarkBase;
	}

	/**
	 * sets the basic shelfmark for this work
	 *
	 * @param shelfmarkBase
	 *            the basic shelfmark
	 */
	public void setShelfmarkBase(String shelfmarkBase) {
		this.shelfmarkBase = shelfmarkBase;
	}

	/**
	 * adds a document to this work
	 *
	 * @param document
	 *            the document to be added
	 */
	public void addDocument(Manifestation document) {
		documents.add(document);
	}

	/**
	 * checks whether a document is already in this work
	 *
	 * @return boolean true if work contains document
	 * @param document
	 *            the document to be tested
	 */
	public boolean contains(Manifestation document) {
		return document.getShelfmarkBase().equals(this.shelfmarkBase);
	}

	/**
	 * returns all documents of this work
	 *
	 * @return documents the list of documents
	 */

	public List<Manifestation> getDocuments() {
		return documents;
	}

	/**
	 * returns the events of all items belonging to this work
	 *
	 * @return events the list of events
	 */
	public List<Event> getEvents() {
		List<Event> events = new ArrayList<>();
		for (Manifestation document : documents) {
			events.addAll(document.getEvents());
		}
		return events;
	}

	/**
	 * returns the items belonging to this work
	 *
	 * @return items the list of items
	 */
	public List<Item> getItems() {
		List<Item> items = new ArrayList<>();
		for (Manifestation document : documents) {
			items.addAll(document.getItems());
		}
		return items;
	}

	/**
	 * returns the document with the specified document number from this work
	 *
	 * @param titleID
	 *            the document number of a document within the work
	 * @return document the document with the corresponding document number
	 */
	public Manifestation getDocument(String titleID) {
		for (Manifestation document : documents)
			if (document.getTitleID().equals(titleID))
				return document;
		return null;
	}

	/**
	 * returns compares two works by their basic shelfmarks
	 *
	 * @param other
	 *            another work
	 * @return document true, if the basic shelfmarks are identical
	 */
	@Override
	public boolean equals(Object other) {
		return shelfmarkBase.equals(((Expression) other).shelfmarkBase);
	}

	/**
	 * returns a hash code for this work
	 *
	 * @return haschCode a hash for this work
	 */
	@Override
	public int hashCode() {
		return shelfmarkBase.hashCode();
	}
	
	/**
	 * @return the bibliographicInformation
	 */
	public BibliographicInformation getBibliographicInformation() {
		return bibliographicInformation;
	}

	/**
	 * @param bibliographicInformation the bibliographicInformation to set
	 */
	public void setBibliographicInformation(BibliographicInformation bibliographicInformation) {
		this.bibliographicInformation = bibliographicInformation;
	}

	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(List<Manifestation> documents) {
		this.documents = documents;
	}

	/**
	 * instantiates a clone of the object
	 *
	 * @return a cloned object
	 */
	public Expression clone() {
	    Expression clone = new Expression(shelfmarkBase);
	    for (Manifestation document : documents)
	        clone.addDocument(document);
	    return clone;
	}

}
