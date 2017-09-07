package unidue.ub.media.analysis;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Plain old java object holding an analysis of one year and one document. The
 * fields can be persisted.
 * 
 * @author Eike Spielberg
 * @version 1
 */
@Entity
public class Eventanalysis {

	@Column(columnDefinition = "TEXT")
	private String mab;

	@Id
	private String identifier;

	private String titleId;

	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	private Date date = new Date();

	private String collection;

	private String systemCode;

	private String materials;

	private String stockcontrolId;

	private String shelfmark;

	private String shelfmarkBase;

	private double meanRelativeLoan;

	private double meanStock;

	private double maxRelativeLoan;

	private double slope;

	private long lastStock;

	private long maxLoansAbs;
	
	private long proposedDeletion;

	@Column(columnDefinition = "TEXT")
	private String comment;
	
	private String status;

	private long maxNumberRequest;
	
	private long maxItemsNeeded;
	
	private long daysRequested;
	
	private long numberRequests;
	
	private long proposedPurchase;

	/**
	 * Builds a new instance of a <code>EventAnalysis</code>-object, setting
	 * the individual counters to 0 and the text fields to an empty string.
	 * 
	 */
	public Eventanalysis() {
		this.identifier = "";
		// general information
		this.mab = "";
		this.titleId = "";
		this.collection = "";
		this.stockcontrolId = "";
		this.materials = "";
		this.shelfmark = "";
		this.shelfmarkBase = "";
		this.lastStock = 0;
		this.meanStock = 0;

		// maximal Loan from timeline
		this.maxRelativeLoan = 0;
		this.maxLoansAbs = 0;

		// calculated mean loan
		this.meanRelativeLoan = 0;

		// calculated properties with scp
		this.proposedDeletion = 0;
		this.comment = "";

		this.maxNumberRequest = 0;
		this.daysRequested = 0;
		this.numberRequests = 0;
		this.maxItemsNeeded = 0;
		this.proposedPurchase = 0;
	}


	/**
	 * retrieves the description of the <code>stockcontrolProperties</code> used
	 * for the analysis
	 * 
	 * @return stockcontrolId description of the
	 *         <code>stockcontrolProperties</code>
	 */
	public String getStockcontrolId() {
		return stockcontrolId;
	}

	/**
	 * set the description of the <code>stockcontrolProperties</code> used for
	 * the analysis
	 * 
	 * @param stockcontrolId
	 *            description of the <code>stockcontrolProperties</code>
	 */
	public void setStockcontrolId(String stockcontrolId) {
		this.stockcontrolId = stockcontrolId;
		this.identifier = stockcontrolId + "-" + titleId;
	}

	/**
	 * retrieves the shelfmark where the analysis has been performed
	 * 
	 * @return shelfmark shelfmark
	 */
	public String getShelfmark() {
		return shelfmark;
	}

	/**
	 * set the shelfmark where the analysis has been performed
	 * 
	 * @param shelfmark
	 *            shelfmark
	 */
	public void setShelfmark(String shelfmark) {
		this.shelfmark = shelfmark;
	}

	/**
	 * retrieves the notation where the analysis has been performed
	 * 
	 * @return systemCode notation
	 */
	public String getSystemCode() {
		return systemCode;
	}

	/**
	 * set the notation where the analysis has been performed
	 * 
	 * @param systemCode
	 *            notation
	 */
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	/**
	 * retrieves the collections to be analyzed (as list, separated with blanks)
	 * 
	 * @return collection list of collections (separated by blanks)
	 */
	public String getCollection() {
		return collection;
	}

	/**
	 * set the collections to be analyzed (as list, separated with blanks)
	 * 
	 * @param collection
	 *            list of collections (separated by blanks)
	 */
	public void setCollection(String collection) {
		this.collection = collection;
	}

	/**
	 * retrieves the list of materials to be considered, each separated by
	 * blanks
	 * 
	 * @return materials list of materials to be considered
	 */
	public String getMaterials() {
		return materials;
	}

	/**
	 * set the list of materials to be considered, each separated by blanks
	 * 
	 * @param materials
	 *            list of materials to be considered
	 */
	public void setMaterials(String materials) {
		this.materials = materials;
	}

	/**
	 * retrieves the timestamp when the analysis has been performed
	 * 
	 * @return timestamp timestamp
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * set the timestamp when the analysis has been performed
	 * 
	 * @param date
	 *            timestamp
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * retrieves the bibliographic data
	 * 
	 * @return mab bibliographic data
	 */
	public String getMab() {
		return mab;
	}

	/**
	 * set the bibliographic data
	 * 
	 * @param mab
	 *            bibliographic data
	 */
	public void setMab(String mab) {
		this.mab = mab;
	}

	/**
	 * retrieves the comment of the analysis
	 * 
	 * @return comment comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * set the number of items proposed to be deleted
	 * 
	 * @param newProposedDeletion
	 *            number of items proposed to be deleted
	 */
	public void setProposedDeletion(long newProposedDeletion) {
		this.proposedDeletion = newProposedDeletion;
	}

	/**
	 * retrieves the mean relative loan of items in stock throughout the
	 * analysis
	 * 
	 * @return meanRelativeLoan mean relative loan
	 */
	public double getMeanRelativeLoan() {
		return meanRelativeLoan;
	}

	/**
	 * retrieves the mean number of items in stock throughout the analysis
	 * 
	 * @return meanStock mean number of items in stock
	 */
	public double getMeanStock() {
		return meanStock;
	}

	/**
	 * retrieves the titleId of the analysis
	 * 
	 * @return titleId titleId
	 */
	public String getTitleId() {
		return titleId;
	}

	/**
	 * set the titleId of the analysis
	 * 
	 * @param titleId
	 *            titleId
	 */
	public void setTitleId(String titleId) {
		this.titleId = titleId;
		this.identifier = stockcontrolId + "-" + titleId;
	}

	/**
	 * retrieves the maximum relative loan throughout the period of analysis
	 * 
	 * @return maxRelativeLoan maximum relative loan
	 */
	public double getMaxRelativeLoan() {
		return maxRelativeLoan;
	}

	/**
	 * set the maximum relative loan throughout the period of analysis
	 * 
	 * @param maxRelativeLoan
	 *            maximum relative loan
	 */
	public void setMaxRelativeLoan(double maxRelativeLoan) {
		this.maxRelativeLoan = maxRelativeLoan;
	}

	/**
	 * retrieves the slope of the relative loan throughout the analysis
	 * 
	 * @return slope slope of the relative loan
	 */
	public double getSlope() {
		return slope;
	}

	/**
	 * set the slope of the relative loan throughout the analysis
	 * 
	 * @param slope
	 *            slope of the relative loan
	 */
	public void setSlope(double slope) {
		this.slope = slope;
	}

	/**
	 * retrieves the number of items at the end of the analysis period
	 * 
	 * @return lastStock number of items at the end of the analysis period
	 */
	public long getLastStock() {
		return lastStock;
	}

	/**
	 * set the number of items at the end of the analysis period
	 * 
	 * @param lastStock
	 *            number of items at the end of the analysis period
	 */
	public void setLastStock(long lastStock) {
		this.lastStock = lastStock;
	}

	/**
	 * retrieves the maximum number of items loaned throughout the period of
	 * analysis
	 * 
	 * @return maxLoansAbs maximum number of items loaned
	 */
	public long getMaxLoansAbs() {
		return maxLoansAbs;
	}

	/**
	 * set the maximum number of items loaned throughout the period of analysis
	 * 
	 * @param maxLoansAbs
	 *            maximum number of items loaned
	 */
	public void setMaxLoansAbs(long maxLoansAbs) {
		this.maxLoansAbs = maxLoansAbs;
	}

	/**
	 * retrieves the maximum number of items requested throughout the period of
	 * analysis
	 * 
	 * @return maxNumberRequest maximum number of items requested
	 */
	public long getMaxNumberRequest() {
		return maxNumberRequest;
	}

	/**
	 * increases the number of requests
	 * 
	 */
	public void increaseNumberRequests() {
		numberRequests++;
	}

	/**
	 * set the maximum number of items requested throughout the period of
	 * analysis
	 * 
	 * @param maxNumberRequest
	 *            maximum number of items requested
	 */
	public void setMaxNumberRequest(long maxNumberRequest) {
		this.maxNumberRequest = maxNumberRequest;
	}

	/**
	 * retrieves the maximum number of items needed (stock and requests)
	 * throughout the period of analysis
	 * 
	 * @return maxItemsNeeded maximum number of items needed (stock and
	 *         requests)
	 */
	public long getMaxItemsNeeded() {
		return maxItemsNeeded;
	}

	/**
	 * set the maximum number of items needed (stock and requests) throughout
	 * the period of analysis
	 * 
	 * @param maxItemsNeeded
	 *            maximum number of items needed (stock and requests)
	 */
	public void setMaxItemsNeeded(long maxItemsNeeded) {
		this.maxItemsNeeded = maxItemsNeeded;
	}

	/**
	 * retrieves the days items were requested throughout the period of analysis
	 * 
	 * @return daysRequested days items were requested
	 */
	public long getDaysRequested() {
		return daysRequested;
	}

	/**
	 * set the days items were requested throughout the period of analysis
	 * 
	 * @param daysRequested
	 *            days items were requested
	 */
	public void setDaysRequested(long daysRequested) {
		this.daysRequested = daysRequested;
	}

	/**
	 * retrieves the number of requests throughout the period of analysis
	 * 
	 * @return numberRequests number of requests
	 */
	public long getNumberRequests() {
		return numberRequests;
	}

	/**
	 * set the number of requests throughout the period of analysis
	 * 
	 * @param numberRequests
	 *            number of requests
	 */
	public void setNumberRequests(long numberRequests) {
		this.numberRequests = numberRequests;
	}

	/**
	 * retrieves the number of proposed purchases
	 * 
	 * @return proposedPurchase number of proposed purchases
	 */
	public long getProposedPurchase() {
		return proposedPurchase;
	}

	/**
	 * set the number of proposed purchases
	 * 
	 * @param proposedPurchase
	 *            number of proposed purchases
	 */
	public void setProposedPurchase(long proposedPurchase) {
		this.proposedPurchase = proposedPurchase;
	}

	/**
	 * retrieves the number of proposed deletions
	 * 
	 * @return proposedDeletion number of proposed deletions
	 */
	public long getProposedDeletion() {
		return proposedDeletion;
	}

	/**
	 * set the mean relative loan throughout the period of analysis
	 * 
	 * @param meanRelativeLoan
	 *            the mean relative loan
	 */
	public void setMeanRelativeLoan(double meanRelativeLoan) {
		this.meanRelativeLoan = meanRelativeLoan;
	}

	/**
	 * set the mean stock throughout the period of analysis
	 * 
	 * @param meanStock
	 *            the mean stock
	 */
	public void setMeanStock(double meanStock) {
		this.meanStock = meanStock;
	}

	/**
	 * set the comment of the analysis
	 * 
	 * @param comment
	 *            the comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * retrieve the basic shelfmark without the edition information
	 * 
	 * @return the shelfmarkBase
	 */
	public String getShelfmarkBase() {
		return shelfmarkBase;
	}

	/**
	 * set the basic shelfmark without the edition information
	 * 
	 * @param shelfmarkBase
	 *            the shelfmarkBase to set
	 */
	public void setShelfmarkBase(String shelfmarkBase) {
		this.shelfmarkBase = shelfmarkBase;
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
