/*
 * 
 */
package unidue.ub.media.monographs;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Representation of one manifestation characterized by given document number
 * and a specific callNo (shelfmark). One <code>Document</code> can contain
 * several <code>Item</code> objects and gives access to the <code>Event</code>
 * objects connected to these items. It also holds the Bibliographic data in
 * MAB-xml format
 * 
 * @author Frank L\u00FCtzenkirchen, Eike Spielberg
 * @version 1
 */
public class Manifestation implements Cloneable {

	private final static Pattern editionFinder = Pattern.compile(".*\\((\\d+)\\).*");

	private String titleID = "";

	private String shelfmark = "";

	private String shelfmarkBase = "";

	private List<Item> items = new ArrayList<>();

	private String edition = "1";

	private BibliographicInformation bibliographicInformation;

	private Set<String>  collections;

	private Set<String> materials;

	public Manifestation() {
	}

	public Manifestation(String titleID) {
		this.titleID = titleID;
		collections = new HashSet<>();
		materials = new HashSet<>();
	}


	public void setTitleID(String titleID) {
		this.titleID = titleID;
	}

	public String getTitleID() {
		return titleID;
	}

	public void setShelfmark(String shelfmark) {
		this.shelfmark = shelfmark;
	}

	public String getShelfmark() {
		return shelfmark;
	}

	public void setShelfmarkBase(String shelfmarkBase) {
		this.shelfmarkBase = shelfmarkBase;
	}

	public String getShelfmarkBase() {
		return shelfmarkBase;
	}

	public String getEdition() {
		return edition;
	}

	public List<String> getCollections() {
		return new ArrayList<String>(collections);
	}

	public List<String> getMaterials() { return new ArrayList<String>(materials); }


	public void addItem(Item item) {
		items.add(item);
		addItemShelfmarkIfNew(item);
		if (!collections.contains(item.getCollection()))
		collections.add(item.getCollection());
		if (!materials.contains(item.getMaterial()))
			materials.add(item.getMaterial());
	}

	public void addItems(List<Item> items) {
		for (Item item : items) {
			addItem(item);
		}
	}


	public List<Item> getItems() {
		return items;
	}

	public Item getItem(String itemId) {
		for (Item item : items)
			if (item.getItemId().equals(itemId))
				return item;
		return null;
	}


	public void setItmes(List<Item> items) {
		this.items = items;
	}

	public BibliographicInformation getBibliographicInformation() {
		return bibliographicInformation;
	}

	public void setBibliographicInformation(BibliographicInformation bibliographicInformation) {
		this.bibliographicInformation = bibliographicInformation;
	}

	@JsonIgnore
	public List<Event> getEvents() {
		List<Event> events = new ArrayList<>();
		for (Item item : getItems())
			events.addAll(item.getEvents());
		Collections.sort(events);
		return events;
	}

	@Override
	public boolean equals(Object other) {
		return shelfmark.equals(((Manifestation) other).shelfmark);
	}

	@Override
	public int hashCode() {
		return titleID.trim().hashCode();
	}

	public Manifestation clone() {
		Manifestation clone = new Manifestation(shelfmark);
		for (Item item : items)
			clone.addItem(item);
		return clone;
	}

	@JsonIgnore
	public String[] getShelfmarks() {
		return shelfmark.split(", ");
	}


	private void addItemShelfmarkIfNew(Item item) {
		String shelfmarkItem = item.getShelfmark().replaceAll("\\+\\d+", "");
		if ((shelfmarkItem == null) || (shelfmarkItem.equals(Item.UNKNOWN)) || shelfmarkItem.isEmpty()) {
			return;
		} else if (!shelfmark.contains(shelfmarkItem))
			addShelfmark(shelfmarkItem);
	}

	private void addShelfmark(String shelfmark) {
		if (!this.shelfmark.isEmpty())
			this.shelfmark += ", ";
		this.shelfmark += shelfmark;
		buildEdition(this.shelfmark);
		buildShelfmarkBase(this.shelfmark);
	}

	private void buildShelfmarkBase(String shelfmark) {
		shelfmarkBase = editionFinder.matcher(shelfmark).matches() ? shelfmark.replaceAll("\\((\\d+)\\)", "") : shelfmark;
	}

	private void buildEdition(String shelfmark) {
		Matcher m = editionFinder.matcher(shelfmark);
		edition = m.matches() ? m.group(1) : "1";
	}

	public boolean contains(String shelfmark) {
		return this.shelfmark.contains(shelfmark);
}
}