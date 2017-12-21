/**
 * 
 */
package unidue.ub.media.journals;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

/**
 * Plain old java object as representation of a journal, collecting all different types (electronic/ print) and changes throughout time (publisher change, name changes etc.) 
 * @author Eike Spielberg
 * @version 1
 */
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "zdbid")
@Entity
public class Journal implements Cloneable {

	@Id
	private String zdbid;
	
	@Column(columnDefinition = "TEXT")
	private String actualName;

	private String ezbid;
	
	@Column(columnDefinition = "TEXT")
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String link;
	
	@Column(columnDefinition = "TEXT")
	private String publisher;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "journal")
	private List<Journaltitle> journaltitles;

	@ManyToOne
	@JoinColumn(name="journalcollection_identifier")
	private Journalcollection journalcollection;

	public Journal() {
		actualName = "";
		ezbid = "";
		zdbid = "";
		link = "";
		subject = "";
		publisher = "";
		journaltitles = new ArrayList<>();
	}

	public Journal(String zdbid, Journalcollection journalcollection) {
		this.zdbid = zdbid;
		this.journalcollection = journalcollection;
		actualName = "";
		ezbid = "";
		link = "";
		subject = "";
		publisher = "";
		journaltitles = new ArrayList<>();
	}


	public Journalcollection getJournalcollection() {
		return journalcollection;
	}

	public List<Journaltitle> getJournaltitles() {
		return journaltitles;
	}

	public Journal setJournaltitles(List<Journaltitle> journaltitles) {
		this.journaltitles = journaltitles;
		return this;
	}

	public Journal addJournalTitle(Journaltitle journaltitle) {
		journaltitles.add(journaltitle);
		return this;
	}

	public String getZdbid() {
		return zdbid;
	}

	public String getEzbid() {
		return ezbid;
	}

	public Journal setZdbid(String zdbid) {
		this.zdbid = zdbid;
		return this;
	}

	public Journal setEzbID(String ezbID) {
		this.ezbid = ezbID;
		return this;
	}
	
	public Journal setActualName(String actualName) {
		this.actualName = actualName;
		return this;
	}
	
	public Journal setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}

	public Journal setLink(String link) {
		this.link = link;
		return this;
	}

	public String getActualName() {
		return actualName;
	}

	public String getSubject() {
		return subject;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getLink() {
		return link;
	}
	
	public Journal setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	@JsonIgnore
	public String getIssns() {
		String issns = "";
		for (Journaltitle journaltitle : getJournaltitles()) {
			if (!issns.isEmpty())
				issns += ";";
			issns += journaltitle.getIssn();
		}
		return issns;
	}

	@JsonIgnore
	public List<String> getSubjectList() {
		List<String> subjects = new ArrayList<>();
		if (subject.contains(",")) {
			subjects = Arrays.asList(subject.split(";"));
		} else {
			subjects.add(subject);
		}
		return subjects;
	}

	@JsonIgnore
	public List<Journaltitle> getElectronicJournals(){
		List<Journaltitle> electronicJournals = new ArrayList<>();
		for (Journaltitle journaltitle : journaltitles) {
			if (journaltitle.getType().equals("electronic"))
				electronicJournals.add(journaltitle);
		}
		return electronicJournals;
	}

	@JsonIgnore
	public List<Journaltitle> getPrintJournals(){
		List<Journaltitle> printJournals = new ArrayList<>();
		for (Journaltitle journaltitle : journaltitles) {
			if (journaltitle.getType().equals("print"))
				printJournals.add(journaltitle);
		}
		return printJournals;
	}

	public Journal clone() {
		Journal clone = new Journal(zdbid,journalcollection)
				.setActualName(actualName)
				.setJournaltitles(journaltitles)
				.setLink(link)
				.setPublisher(publisher)
				.setSubject(subject)
				.setEzbID(ezbid);
		return clone;
	}
}
