/**
 * 
 */
package unidue.ub.media.analysis;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;


/**
 * Plain Old Java Object as representation of the COUNTER statistics obtained by SUSHI requests.
 * @author Spielberg
 *
 */
@Entity
public class JournalCounter extends Counter {
    
    @Id
    private String id;
    
    private String printIssn = "";
    
    private String onlineIssn = "";
    
    private String category = "";
    
    private String doi = "";
    
    private String proprietary = "";
    
    private String abbreviation = "";
    
    private String fullName = "";
    
    private String publisher = "";

    private String platform = "";
    
    private String type = "";
    
    private int year;
    
    private int month;
    
    private long htmlRequests = 0L;
    
    private long htmlRequestsMobile = 0L;
    
    private long pdfRequests = 0L;
    
    private long pdfRequestsMobile = 0L;
    
    private long psRequests = 0L;
    
    private long psRequestsMobile = 0L;
    
    private long totalRequests = 0L;

	/**
	 * @return the doi
	 */
	public String getDoi() {
		return doi;
	}

	/**
	 * @return the proprietary
	 */
	public String getProprietary() {
		return proprietary;
	}

	
	/**
	 * @param Isbn the Isbn to set
	 */
	public void setIsbn(String Isbn) {
		Isbn = Isbn;
	}

	
	/**
	 * @param Issn the Issn to set
	 */
	public void setIssn(String Issn) {
		Issn = Issn;
	}

	
	/**
	 * @param doi the doi to set
	 */
	public void setDoi(String doi) {
		this.doi = doi;
	}

	/**
	 * @param proprietary the proprietary to set
	 */
	public void setProprietary(String proprietary) {
		this.proprietary = proprietary;
	}

	/**
     * general constructor and initialization
     */
    public JournalCounter() {
    	LocalDate today = LocalDate.now();
    	onlineIssn = "";
    	month = today.getMonthValue();
    	year = today.getYear();
    	id = year + month + onlineIssn+platform;
    }

	public JournalCounter(String onlineIssn, String platform, int month, int year) {
		this.onlineIssn = onlineIssn;
		this.platform = platform;
		this.month = month;
		this.year = year;
		id = year + month  + onlineIssn + platform;
	}

    /**
     * returns the Issns of print journals
     * @return the prlongIssn
     */
    public String getPrintIssn() {
        return printIssn;
    }

    /**
     * returns the Issns of online journals
     * @return the onlineIssn
     */
    public String getOnlineIssn() {
        return onlineIssn;
    }

    /**
     * returns the abbreviation of the journal
     * @return the abbreviation
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * returns the full name of the journal
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * returns the SUSHI provider of the journal
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * returns the type of the COUNTER report (e.g. JR1)
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * returns the year of the report
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * retursn the month of the report
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * returns the number of successful HTML request
     * @return the htmlRequests
     */
    public long getHtmlRequests() {
        return htmlRequests;
    }

    /**
     * returns the number of successful mobile HTML requests
     * @return the htmlRequestsMobile
     */
    public long getHtmlRequestsMobile() {
        return htmlRequestsMobile;
    }

    /**
     * returns the number of successful PDF requests
     * @return the pdfRequests
     */
    public long getPdfRequests() {
        return pdfRequests;
    }

    /**
     * returns the number of successful mobile PDF requests
     * @return the pdfRequestsMobile
     */
    public long getPdfRequestsMobile() {
        return pdfRequestsMobile;
    }

    /**
     * returns the number of successful PostScript requests
     * @return the psRequests
     */
    public long getPsRequests() {
        return psRequests;
    }

    /**
     * returns the number of successful mobile PostScript requests
     * @return the psRequestsMobile
     */
    public long getPsRequestsMobile() {
        return psRequestsMobile;
    }

    /**
     * returns the total number of successful requests independent of the type
     * @return the totalRequests
     */
    public long getTotalRequests() {
        return totalRequests;
    }

    /**
     * sets the Issns of printIssn journals 
     * @param printIssn the printIssn to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setPrintIssn(String printIssn) {
        this.printIssn = printIssn;
        return this;
    }

    /**
     * sets the Issns of online journals
     * @param onlineIssn the onlineIssn to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setOnlineIssn(String onlineIssn) {
        this.onlineIssn = onlineIssn;
		id = year + month + onlineIssn + platform;
        return this;
    }

    /**
     * sets the abbreviation of the journal
     * @param abbreviation the abbreviation to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
        return this;
    }

    /**
     * sets the full name of the journal
     * @param fullName the fullName to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * sets the SUSHI provider of the journal
     * @param publisher the publisher to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    /**
     * sets the type of the COUNTER report (e.g. JR1)
     * @param type the type to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setType(String type) {
        this.type = type;
        return this;
    }

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
		id = year + month + onlineIssn + platform;
	}

	/**
     * sets the year of the report
     * @param year the year to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setYear(int year) {
        this.year = year;
		id = year + month + onlineIssn + platform;
        return this;
    }

    /**
     * sets the month of the report
     * @param month the month to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setMonth(int month) {
        this.month = month;
		id = year + month + onlineIssn + platform;
        return this;
    }

    /**
     * sets the number of successful HTML request
     * @param htmlRequests the htmlRequests to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setHtmlRequests(long htmlRequests) {
        this.htmlRequests = htmlRequests;
        return this;
    }

    /**
     * sets the number of successful mobile HTML request
     * @param htmlRequestsMobile the htmlRequestsMobile to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setHtmlRequestsMobile(long htmlRequestsMobile) {
        this.htmlRequestsMobile = htmlRequestsMobile;
        return this;
    }

    /**
     * sets the number of successful PDF request
     * @param pdfRequests the pdfRequests to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setPdfRequests(long pdfRequests) {
        this.pdfRequests = pdfRequests;
        return this;
    }

    /**
     * sets the number of successful mobile PDF request
     * @param pdfRequestsMobile the pdfRequestsMobile to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setPdfRequestsMobile(long pdfRequestsMobile) {
        this.pdfRequestsMobile = pdfRequestsMobile;
        return this;
    }

    /**
     * sets the number of successful PostScript request
     * @param psRequests the psRequests to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setPsRequests(long psRequests) {
        this.psRequests = psRequests;
        return this;
    }

    /**
     * sets the number of successful mobile PostScript request
     * @param psRequestsMobile the psRequestsMobile to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setPsRequestsMobile(long psRequestsMobile) {
        this.psRequestsMobile = psRequestsMobile;
        return this;
    }

    /**
     * sets the total number of successful requests independent of the type
     * @param totalRequests the totalRequests to set
     * @return JournalCounter the updated object
     */
    public JournalCounter setTotalRequests(long totalRequests) {
        this.totalRequests = totalRequests;
        return this;
    }

	public String getId() {
		return id;
	}
    
    /**
     * adds another COUNTER report to the actual one. The request fields of the other report are added to the ones of the actual report, 
     * the String fields are retained from the actual COUNTER report. 
     * @param other the other COUNTER report, the actual one is compared to
     * @return JournalCounter the updated object
     */
    public JournalCounter add(JournalCounter other) {
        htmlRequests = htmlRequests + other.getHtmlRequests();
        htmlRequestsMobile = htmlRequestsMobile + other.getHtmlRequestsMobile();
        pdfRequests = pdfRequests + other.getPdfRequests();
        pdfRequestsMobile = pdfRequestsMobile + other.getPdfRequestsMobile();
        psRequests = psRequests + other.getPsRequests();
        psRequestsMobile = psRequestsMobile + other.getPsRequestsMobile();
        totalRequests = psRequestsMobile + other.getTotalRequests();
        return this;
    }

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
}
