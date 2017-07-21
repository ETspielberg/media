package unidue.ub.media.monographs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Representation object of the basic bibliographic information of one document
 * 
 * @author Eike Spielberg
 * @version 1
 */
@Entity
public class BibliographicInformation {

	@Id
	private String titleId;
	
	private String isbn;
	
	private String doi;

	private List<String> authors;

	@Column(columnDefinition = "TEXT")
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String subtitle;

	private String publisher;

	private String place;

	private String year;
	
	private String edition;
	
	private String series;
	
	private int volume;
	
	private List<String> keywords;

	private String type;

	/**
	 * creates a new <code>Publication</code>-object
	 *
	 * @param titleId
	 *            the document number
	 * @param authors
	 *            the authors of the publication
	 * @param title
	 *            the title of the publication
	 * 
	 */
	public BibliographicInformation(String titleId, List<String> authors, String title) {
		this.titleId = titleId;
		this.authors = authors;
		this.title = title;
		this.type = "basic";
	}

	public BibliographicInformation() {
		isbn = "";
		authors = new ArrayList<>();
		title = "";
		publisher = "";
		place = "";
		year = "";
		edition = "";
		series = "";
		volume = 0;
		keywords = new ArrayList<>();
		type = "empty";
	}

	/**
	 * creates a new <code>Publication</code>-object
	 *
	 * @param docNumber
	 *            the document number
	 * @param isbn
	 *            the isbn of the publication
	 * @param authors
	 *            the authors of the publication
	 * @param title
	 *            the title of the publication
	 * @param publisher
	 *            the publisher of the publication
	 * @param place
	 *            the place where the publication was published
	 * @param year
	 *            the year the publication was published
	 * 
	 */


	/**
	 * returns the type of the publication
	 *
	 * @return type the type of the publication
	 */
	public String getType() {
		return type;
	}

	/**
	 * returns the ISBN of the publication
	 *
	 * @return isbn the ISBN of the publication
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * returns the authors of the publication
	 *
	 * @return authors the authors of the publication
	 */
	public List<String> getAuthors() {
		return authors;
	}

	/**
	 * returns the title of the publication
	 *
	 * @return title the title of the publication
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * returns the publisher of the publication
	 *
	 * @return publisher the publisher of the publication
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * returns the place the publication was published
	 *
	 * @return place the place the publication was published
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * returns the year the publication was published
	 *
	 * @return year the year the publication was published
	 */
	public String getYear() {
		return year;
	}

	/**
	 * sets the ISBN of the publication
	 *
	 * @param newIsbn
	 *            the ISBN
	 */
	public void setIsbn(String newIsbn) {
		this.isbn = newIsbn;
	}

	/**
	 * adds an author to the author list
	 *
	 * @param author
	 *            the name of the author
	 */
	public void addAuthor(String author) {
		authors.add(author);
	}

	/**
	 * sets the authors of the publication
	 *
	 * @param newAuthors
	 *            list of author names
	 */
	public void setAuthors(List<String> newAuthors) {
		this.authors = newAuthors;
	}

		/**
	 * sets the publisher of the publication
	 *
	 * @param newPublisher
	 *            the name of the publisher
	 */
	public void setPublisher(String newPublisher) {
		this.publisher = newPublisher;
	}

	/**
	 * sets the place the publication was published
	 *
	 * @param newPlace
	 *            the name of the place
	 */
	public void setPlace(String newPlace) {
		this.isbn = newPlace;
	}

	/**
	 * sets the year the publication was published
	 *
	 * @param newYear
	 *            the year
	 */
	public void setYear(String newYear) {
		this.isbn = newYear;
	}

	/**
	 * @return the edition
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * @return the series
	 */
	public String getSeries() {
		return series;
	}

	/**
	 * @return the volume
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * @return the keywords
	 */
	public List<String> getKeywords() {
		return keywords;
	}

	/**
	 * @param edition the edition to set
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}

	/**
	 * @param series the series to set
	 */
	public void setSeries(String series) {
		this.series = series;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * @param keyword the keyword to add
	 */
	public void addKeyword(String keyword) {
		keywords.add(keyword);
	}
	
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the doi
	 */
	public String getDoi() {
		return doi;
	}

	/**
	 * @param doi the doi to set
	 */
	public void setDoi(String doi) {
		this.doi = doi;
	}

	/**
	 * @return the docNumber
	 */
	public String getTitleId() {
		return titleId;
	}

	/**
	 * @param titleId the docNumber to set
	 */
	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	

}
