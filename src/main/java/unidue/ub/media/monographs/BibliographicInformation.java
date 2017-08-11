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

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void addAuthor(String author) {
		authors.add(author);
	}


	public void addKeyword(String keyword) {
		keywords.add(keyword);
	}

}
