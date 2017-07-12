package unidue.ub.media.analysis;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class JournalRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String issn;
	
	@Column(columnDefinition = "TEXT")
	private String name;
	
	private String volume;
	
	private String yearOfVolume;
	
	private String collection;
	
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	private Date date = new Date();
	
	public JournalRequest() {
		issn = "";
		name="";
		volume = "";
		yearOfVolume = "";
		collection = "";
	}

	/**
	 * @return the issn
	 */
	public String getIssn() {
		return issn;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the volume
	 */
	public String getVolume() {
		return volume;
	}

	/**
	 * @return the yearOfVolume
	 */
	public String getYearOfVolume() {
		return yearOfVolume;
	}

	/**
	 * @return the collection
	 */
	public String getCollection() {
		return collection;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param issn the issn to set
	 */
	public void setIssn(String issn) {
		this.issn = issn;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}

	/**
	 * @param yearOfVolume the yearOfVolume to set
	 */
	public void setYearOfVolume(String yearOfVolume) {
		this.yearOfVolume = yearOfVolume;
	}

	/**
	 * @param collection the collection to set
	 */
	public void setCollection(String collection) {
		this.collection = collection;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
