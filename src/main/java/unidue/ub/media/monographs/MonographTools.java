package unidue.ub.media.monographs;

import java.util.HashSet;
import java.util.List;


import org.apache.commons.lang3.StringUtils;

import org.jdom2.Element;
import org.mycore.common.content.MCRJDOMContent;
import org.mycore.common.content.transformer.MCRXSLTransformer;

public class MonographTools {

	private final int LEVENTHRESHOLD = 100;

	/**
	 * returns the Levenshtein distance comparing the authors and title of two
	 * publications
	 *
	 * @param other
	 *            other <code>Publication</code>-object to be compared to
	 * @return levDist Levenshtein distance
	 */
	public int getLevDist(BibliographicInformation first, BibliographicInformation other) {
		// compares author and title and returns the total Levenhtein distance.
		int authorLeven = 0;
		List<String> authors = first.getAuthors();
		if (first.getAuthors().size() == other.getAuthors().size()) {
			for (int i = 0; i < authors.size(); i++)
				authorLeven += StringUtils.getLevenshteinDistance(authors.get(i), other.getAuthors().get(i));
		} else {
			authorLeven = LEVENTHRESHOLD;
		}
		int titleLeven = 0;

		// for title comparison take only the first items of the list, in case
		// of different depth of catalogueing with respect to sub-titles.
		titleLeven += StringUtils.getLevenshteinDistance(first.getTitle(), other.getTitle());
		int totalLeven = authorLeven + titleLeven;
		return totalLeven;
	}

	/**
	 * creates a new <code>Publication</code>-object from a document
	 *
	 * @param mabxml
	 *            the MAB-XML data as JDOM Element
	 * 
	 */
	public BibliographicInformation buildBibligraphicInformationFromMABXML(Element mabxml) {
		BibliographicInformation bibliographicInformation = new BibliographicInformation();
		MCRJDOMContent source = new MCRJDOMContent(mabxml);
		MCRXSLTransformer transformer = new MCRXSLTransformer("xsl/mabxml2bibliographicInformation.xsl");
		HashSet<String> keywordsSet = new HashSet<>();
		try {
			Element result = transformer.transform(source).asXML().detachRootElement().clone();

			List<Element> authors = result.getChild("author").getChildren();
			for (Element author : authors) {
				bibliographicInformation.addAuthor(author.getText());
			}

			List<Element> keywords = result.getChild("keywords").getChildren();
			for (Element keyword : keywords) {
				String keywordText = keyword.getText();
				if (!keywordsSet.contains(keywordText)) {
					bibliographicInformation.addKeyword(keyword.getText());
					keywordsSet.add(keywordText);
				}
			}
			bibliographicInformation.setIsbn(result.getChildText("isbn"));
			bibliographicInformation.setDoi(result.getChildText("doi"));
			bibliographicInformation.setEdition(result.getChildText("edition"));
			bibliographicInformation.setPlace(result.getChildText("place"));
			bibliographicInformation.setPublisher(result.getChildText("publisher"));
			bibliographicInformation.setSeries(result.getChildText("series"));
			bibliographicInformation.setSubtitle(result.getChildText("subtitle"));
			bibliographicInformation.setTitle(result.getChildText("title"));
			bibliographicInformation.setYear(result.getChildText("year"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bibliographicInformation;
	}

}
