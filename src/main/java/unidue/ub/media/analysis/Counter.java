/**
 * 
 */
package unidue.ub.media.analysis;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Plain Old Java Object as representation of the COUNTER statistics obtained by SUSHI requests.
 * @author Spielberg
 *
 */
@Entity
public class Counter implements Comparable<Counter> {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String printISSN;
    
    private String onlineISSN;
    
    private String eISSN;
    
    private String ISSN;
    
    private String ISBN;
    
    private String category;
    
    private String onlineISBN;
    
    private String PrintISBN;
    
    private String doi;
    
    private String proprietary;
    
    private String abbreviation;
    
    private String fullName;
    
    private String publisher;
    
    private String type;
    
    private int year;
    
    private int month;
    
    private BigInteger htmlRequests;
    
    private BigInteger htmlRequestsMobile;
    
    private BigInteger pdfRequests;
    
    private BigInteger pdfRequestsMobile;
    
    private BigInteger psRequests;
    
    private BigInteger psRequestsMobile;
    
    private BigInteger ePub;
    
    private BigInteger totalRequests;
    
    private BigInteger sectionedHtml;

    private BigInteger toc;

    private BigInteger abstractCounter;

    private BigInteger reference;

    private BigInteger dataSet;

    private BigInteger audio;

    private BigInteger video;

    private BigInteger image;

    private BigInteger podcast;

    private BigInteger multimedia;

    private BigInteger recordView;

    private BigInteger resultClick;

    private BigInteger searchReg;

    private BigInteger searchFed;

    private BigInteger turnaway;

    private BigInteger noLicense;

    private BigInteger other;

    
    /**
	 * @return the eISSN
	 */
	public String geteISSN() {
		return eISSN;
	}

	/**
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * @return the onlineISBN
	 */
	public String getOnlineISBN() {
		return onlineISBN;
	}

	/**
	 * @return the prBigInteger_ISBN
	 */
	public String getPrintISBN() {
		return PrintISBN;
	}

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
	 * @return the sectionedHtml
	 */
	public BigInteger getSectionedHtml() {
		return sectionedHtml;
	}

	/**
	 * @return the toc
	 */
	public BigInteger getToc() {
		return toc;
	}

	/**
	 * @return the abstractCounter
	 */
	public BigInteger getAbstractCounter() {
		return abstractCounter;
	}

	/**
	 * @return the reference
	 */
	public BigInteger getReference() {
		return reference;
	}

	/**
	 * @return the dataSet
	 */
	public BigInteger getDataSet() {
		return dataSet;
	}

	/**
	 * @return the audio
	 */
	public BigInteger getAudio() {
		return audio;
	}

	/**
	 * @return the video
	 */
	public BigInteger getVideo() {
		return video;
	}

	/**
	 * @return the image
	 */
	public BigInteger getImage() {
		return image;
	}

	/**
	 * @return the podcast
	 */
	public BigInteger getPodcast() {
		return podcast;
	}

	/**
	 * @return the multimedia
	 */
	public BigInteger getMultimedia() {
		return multimedia;
	}

	/**
	 * @return the recordView
	 */
	public BigInteger getRecordView() {
		return recordView;
	}

	/**
	 * @return the resultClick
	 */
	public BigInteger getResultClick() {
		return resultClick;
	}

	/**
	 * @return the searchReg
	 */
	public BigInteger getSearchReg() {
		return searchReg;
	}

	/**
	 * @return the searchFed
	 */
	public BigInteger getSearchFed() {
		return searchFed;
	}

	/**
	 * @return the turnaway
	 */
	public BigInteger getTurnaway() {
		return turnaway;
	}

	/**
	 * @return the noLicense
	 */
	public BigInteger getNoLicense() {
		return noLicense;
	}

	/**
	 * @return the other
	 */
	public BigInteger getOther() {
		return other;
	}

	/**
	 * @param eISSN the eISSN to set
	 */
	public void seteISSN(String eISSN) {
		this.eISSN = eISSN;
	}

	/**
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	/**
	 * @return the iSSN
	 */
	public String getISSN() {
		return ISSN;
	}

	/**
	 * @param iSSN the iSSN to set
	 */
	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}

	/**
	 * @param onlineISBN the onlineISBN to set
	 */
	public void setOnlineISBN(String onlineISBN) {
		this.onlineISBN = onlineISBN;
	}

	/**
	 * @param printISBN the printISBN to set
	 */
	public void setPrintISBN(String PrintISBN) {
		this.PrintISBN = PrintISBN;
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
	 * @param sectionedHtml the sectionedHtml to set
	 */
	public void setSectionedHtml(BigInteger sectionedHtml) {
		this.sectionedHtml = sectionedHtml;
	}

	/**
	 * @param toc the toc to set
	 */
	public void setToc(BigInteger toc) {
		this.toc = toc;
	}

	/**
	 * @param abstractCounter the abstractCounter to set
	 */
	public void setAbstractCounter(BigInteger abstractCounter) {
		this.abstractCounter = abstractCounter;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(BigInteger reference) {
		this.reference = reference;
	}

	/**
	 * @param dataSet the dataSet to set
	 */
	public void setDataSet(BigInteger dataSet) {
		this.dataSet = dataSet;
	}

	/**
	 * @param audio the audio to set
	 */
	public void setAudio(BigInteger audio) {
		this.audio = audio;
	}

	/**
	 * @param video the video to set
	 */
	public void setVideo(BigInteger video) {
		this.video = video;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BigInteger image) {
		this.image = image;
	}

	/**
	 * @param podcast the podcast to set
	 */
	public void setPodcast(BigInteger podcast) {
		this.podcast = podcast;
	}

	/**
	 * @param multimedia the multimedia to set
	 */
	public void setMultimedia(BigInteger multimedia) {
		this.multimedia = multimedia;
	}

	/**
	 * @param recordView the recordView to set
	 */
	public void setRecordView(BigInteger recordView) {
		this.recordView = recordView;
	}

	/**
	 * @param resultClick the resultClick to set
	 */
	public void setResultClick(BigInteger resultClick) {
		this.resultClick = resultClick;
	}

	/**
	 * @param searchReg the searchReg to set
	 */
	public void setSearchReg(BigInteger searchReg) {
		this.searchReg = searchReg;
	}

	/**
	 * @param searchFed the searchFed to set
	 */
	public void setSearchFed(BigInteger searchFed) {
		this.searchFed = searchFed;
	}

	/**
	 * @param turnaway the turnaway to set
	 */
	public void setTurnaway(BigInteger turnaway) {
		this.turnaway = turnaway;
	}

	/**
	 * @param noLicense the noLicense to set
	 */
	public void setNoLicense(BigInteger noLicense) {
		this.noLicense = noLicense;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(BigInteger other) {
		this.other = other;
	}

	/**
     * general constructor and initialization
     */
    public Counter() {
    }

    /**
     * returns the ISSNs of print journals
     * @return the prBigIntegerISSN
     */
    public String getPrintISSN() {
        return printISSN;
    }

    /**
     * returns the ISSNs of online journals
     * @return the onlineISSN
     */
    public String getOnlineISSN() {
        return onlineISSN;
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
    public BigInteger getHtmlRequests() {
        return htmlRequests;
    }

    /**
     * returns the number of successful mobile HTML requests
     * @return the htmlRequestsMobile
     */
    public BigInteger getHtmlRequestsMobile() {
        return htmlRequestsMobile;
    }

    /**
     * returns the number of successful PDF requests
     * @return the pdfRequests
     */
    public BigInteger getPdfRequests() {
        return pdfRequests;
    }

    /**
     * returns the number of successful mobile PDF requests
     * @return the pdfRequestsMobile
     */
    public BigInteger getPdfRequestsMobile() {
        return pdfRequestsMobile;
    }

    /**
     * returns the number of successful PostScript requests
     * @return the psRequests
     */
    public BigInteger getPsRequests() {
        return psRequests;
    }

    /**
     * returns the number of successful mobile PostScript requests
     * @return the psRequestsMobile
     */
    public BigInteger getPsRequestsMobile() {
        return psRequestsMobile;
    }

    /**
     * returns the total number of successful requests independent of the type
     * @return the totalRequests
     */
    public BigInteger getTotalRequests() {
        return totalRequests;
    }

    /**
     * sets the ISSNs of printISSN journals 
     * @param printISSN the printISSN to set
     * @return Counter the updated object
     */
    public Counter setPrintISSN(String printISSN) {
        this.printISSN = printISSN;
        return this;
    }

    /**
     * sets the ISSNs of online journals
     * @param onlineISSN the onlineISSN to set
     * @return Counter the updated object
     */
    public Counter setOnlineISSN(String onlineISSN) {
        this.onlineISSN = onlineISSN;
        return this;
    }

    /**
     * sets the abbreviation of the journal
     * @param abbreviation the abbreviation to set
     * @return Counter the updated object
     */
    public Counter setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
        return this;
    }

    /**
     * sets the full name of the journal
     * @param fullName the fullName to set
     * @return Counter the updated object
     */
    public Counter setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * sets the SUSHI provider of the journal
     * @param publisher the publisher to set
     * @return Counter the updated object
     */
    public Counter setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    /**
     * sets the type of the COUNTER report (e.g. JR1)
     * @param type the type to set
     * @return Counter the updated object
     */
    public Counter setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * sets the year of the report
     * @param year the year to set
     * @return Counter the updated object
     */
    public Counter setYear(int year) {
        this.year = year;
        return this;
    }

    /**
     * sets the month of the report
     * @param month the month to set
     * @return Counter the updated object
     */
    public Counter setMonth(int month) {
        this.month = month;
        return this;
    }

    /**
     * sets the number of successful HTML request
     * @param htmlRequests the htmlRequests to set
     * @return Counter the updated object
     */
    public Counter setHtmlRequests(BigInteger htmlRequests) {
        this.htmlRequests = htmlRequests;
        return this;
    }

    /**
     * sets the number of successful mobile HTML request
     * @param htmlRequestsMobile the htmlRequestsMobile to set
     * @return Counter the updated object
     */
    public Counter setHtmlRequestsMobile(BigInteger htmlRequestsMobile) {
        this.htmlRequestsMobile = htmlRequestsMobile;
        return this;
    }

    /**
     * sets the number of successful PDF request
     * @param pdfRequests the pdfRequests to set
     * @return Counter the updated object
     */
    public Counter setPdfRequests(BigInteger pdfRequests) {
        this.pdfRequests = pdfRequests;
        return this;
    }

    /**
     * sets the number of successful mobile PDF request
     * @param pdfRequestsMobile the pdfRequestsMobile to set
     * @return Counter the updated object
     */
    public Counter setPdfRequestsMobile(BigInteger pdfRequestsMobile) {
        this.pdfRequestsMobile = pdfRequestsMobile;
        return this;
    }

    /**
     * sets the number of successful PostScript request
     * @param psRequests the psRequests to set
     * @return Counter the updated object
     */
    public Counter setPsRequests(BigInteger psRequests) {
        this.psRequests = psRequests;
        return this;
    }

    /**
     * sets the number of successful mobile PostScript request
     * @param psRequestsMobile the psRequestsMobile to set
     * @return Counter the updated object
     */
    public Counter setPsRequestsMobile(BigInteger psRequestsMobile) {
        this.psRequestsMobile = psRequestsMobile;
        return this;
    }

    /**
     * sets the total number of successful requests independent of the type
     * @param totalRequests the totalRequests to set
     * @return Counter the updated object
     */
    public Counter setTotalRequests(BigInteger totalRequests) {
        this.totalRequests = totalRequests;
        return this;
    }
    
    /**
     * compares one COUNTER report to the other. Allows for time-dependent ordering of COUNTER reports. 
     * @param other the other COUNTER report, the actual one is compared to
     * return BigInteger 1, if the actual report dates later than the other one and -1 if it dates earlier.
     */
    public int compareTo(Counter other) {
		if (this.year > other.getYear())
			return 1;
		else if (this.year < other.getYear())
		 return -1;
		else { 
		if (this.month < other.getMonth())
			return -1;
		else if (this.month > other.getMonth())
			return 1;
		else
			return this.month - other.getMonth();
		}
	}
    
    /**
     * adds another COUNTER report to the actual one. The request fields of the other report are added to the ones of the actual report, 
     * the String fields are retained from the actual COUNTER report. 
     * @param other the other COUNTER report, the actual one is compared to
     * @return Counter the updated object
     */
    public Counter add(Counter other) {
        htmlRequests = htmlRequests.add(other.getHtmlRequests());
        htmlRequestsMobile = htmlRequestsMobile.add(other.getHtmlRequestsMobile());
        pdfRequests = pdfRequests.add(other.getPdfRequests());
        pdfRequestsMobile = pdfRequestsMobile.add(other.getPdfRequestsMobile());
        psRequests = psRequests.add(other.getPsRequests());
        psRequestsMobile = psRequestsMobile.add(other.getPsRequestsMobile());
        totalRequests = psRequestsMobile.add(other.getTotalRequests());
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

	/**
	 * @return the ePub
	 */
	public BigInteger getePub() {
		return ePub;
	}

	/**
	 * @param ePub the ePub to set
	 */
	public void setePub(BigInteger ePub) {
		this.ePub = ePub;
	}
}
