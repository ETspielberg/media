/**
 * 
 */
package unidue.ub.media.analysis;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Plain old java object holding the entrie of the requests hitlist. 
 * 
 * @author Eike Spielberg
 * @version 1
 */
@Entity
public class Nrequests implements Cloneable {

	@Column(columnDefinition = "TEXT")
	private String mab;

	@Id
	private String titleId;
	
	private String shelfmark;
	
	private double ratio;
	
	private int NRequests;
	
	private int NItems;
	
	private int NLoans;
	
	private int NLendable;
	
	private int duration;
	
	private String status;
	
	private String alertControl;
	
	private boolean forAlert;
	
	private boolean forReader;
	
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	private Date date = new Date();
	
	/**
	 * general constructor and initialization
	 */
	public Nrequests() {
		titleId = "";
		ratio = 1.0;
		shelfmark = "";
		NRequests = 0;
		NItems = 1;
		NLoans = 0;
		NLendable = 1;
		mab = "";
		duration = 1;
		alertControl = "";
		forAlert = false;
		forReader = false;
	}

	/**
	 * returns the shelfmark
	 * @return the callNo
	 */
	public String getCallNo() {
		return shelfmark;
	}

	/**
	 * returns the duration of requests
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * sets the duration
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * sets the shelfmark
	 * @param callNo the callNo to set
	 */
	public void setCallNo(String callNo) {
		this.shelfmark = callNo;
	}

	/**
	 * returns the bibliographic data
	 * @return the mab
	 */
	public String getMab() {
		return mab;
	}

	/**
	 * sets the bibliographic data
	 * @param mab the mab to set
	 */
	public void setMab(String mab) {
		this.mab = mab;
	}

	/**
	 * returns the document number for the manifestation 
	 * @return the titleId
	 */
	public String getTitleId() {
		return titleId;
	}

	/**
	 * sets the document number for this manifestation
	 * @param titleId the titleId to set
	 */
	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	/**
	 * returns the ratio (number of requested items / number of lendable items)
	 * @return the ratio
	 */
	public double getRatio() {
		return ratio;
	}

	/**
	 * sets the ratio (number of requested items / number of lendable items)
	 * @param ratio the ratio to set
	 */
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	/**
	 * returns the number of requests
	 * @return the nRequests
	 */
	public int getNRequests() {
		return NRequests;
	}

	/**
	 * sets the number of requests
	 * @param nRequests the nRequests to set
	 */
	public void setNRequests(int nRequests) {
		NRequests = nRequests;
	}

	/**
	 * returns the number of items
	 * @return the nItems
	 */
	public int getNItems() {
		return NItems;
	}

	/**
	 * sets the number of items
	 * @param nItems the nItems to set
	 */
	public void setNItems(int nItems) {
		NItems = nItems;
	}

	/**
	 * returns the number of loans
	 * @return the nLoans
	 */
	public int getNLoans() {
		return NLoans;
	}

	/**
	 * sets the number of loans
	 * @param nLoans the nLoans to set
	 */
	public void setNLoans(int nLoans) {
		NLoans = nLoans;
	}

	/**
	 * returns the number of lendable items
	 * @return the nLendable
	 */
	public int getNLendable() {
		return NLendable;
	}

	/**
	 * sets the number of lendable items
	 * @param nLendable the nLendable to set
	 */
	public void setNLendable(int nLendable) {
		NLendable = nLendable;
	}
	
	/**
     * returns the timestamp
     * @return the timestamp
     */
    public Date getDate() {
        return date;
    }

    /**
     * sets the number of date
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
	
	/**
     * returns the alert control 
     * @return the alert control
     */
    public String getAlertControl() {
        return alertControl;
    }

    /**
     * sets the number of alert control
     * @param alertControl the alert control to set
     */
    public void setAlertControl(String alertControl) {
        this.alertControl = alertControl;
    }
    
    /**
     * returns the boolean whether the entry is within the scope of an alert control
     * @return true, if the thresholds are surpassed
     */
    public boolean getForAlert() {
        return forAlert;
    }

    /**
     * sets the boolean whether the entry is within the scope of an alert control
     * @param forAlert true, if the thresholds are surpassed
     */
    public void setForAlert(boolean forAlert) {
        this.forAlert = forAlert;
    }
	
    /**
     * returns the boolean whether the entry is within the scope of a reader control
     * @return true, if the thresholds are surpassed
     */
    public boolean getForReader() {
        return forReader;
    }

    /**
     * sets the boolean whether the entry is within the scope of a reader control
     * @param forReader true, if the thresholds are surpassed
     */
    public void setForReader(boolean forReader) {
        this.forReader = forReader;
    }

	public Nrequests clone() {
	    Nrequests clone = new Nrequests();
	    clone.setAlertControl(alertControl);
	    clone.setCallNo(shelfmark);
	    clone.setTitleId(titleId);
	    clone.setDuration(duration);
	    clone.setMab(mab);
	    clone.setNItems(NItems);
	    clone.setNLendable(NLendable);
	    clone.setNLoans(NLoans);
	    clone.setNRequests(NRequests);
	    return clone;   
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
} 