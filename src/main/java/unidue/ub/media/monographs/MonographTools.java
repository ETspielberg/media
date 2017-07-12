package unidue.ub.media.monographs;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.transform.JDOMSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonographTools {

	private final static int LEVENTHRESHOLD = 100;

	private static final Logger LOGGER = LoggerFactory.getLogger(MonographTools.class);
			
	/**
	 * returns the Levenshtein distance comparing the authors and title of two
	 * publications
	 *
	 * @param other
	 *            other <code>Publication</code>-object to be compared to
	 * @return levDist Levenshtein distance
	 */
	public static int getLevDist(BibliographicInformation first, BibliographicInformation other) {
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
	public static BibliographicInformation buildBibligraphicInformationFromMABXML(Element mabxml) {
		BibliographicInformation bibliographicInformation = new BibliographicInformation();
		HashSet<String> keywordsSet = new HashSet<>();
		try {
			Element result = transformElement(mabxml, "xsl/mabxml2bibliographicInformation.xsl").detachRootElement().clone();
			LOGGER.info(result.toString());

			List<Element> authors = result.getChild("authors").getChildren();
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
			bibliographicInformation.setIsbn(result.getChild("isbn").getValue());
			bibliographicInformation.setDoi(result.getChildText("doi"));
			bibliographicInformation.setEdition(result.getChildText("edition"));
			bibliographicInformation.setPlace(result.getChildText("place"));
			bibliographicInformation.setPublisher(result.getChildText("publisher"));
			bibliographicInformation.setSeries(result.getChildText("series"));
			bibliographicInformation.setSubtitle(result.getChild("subtitle").getValue());
			bibliographicInformation.setTitle(result.getChild("title").getValue());
			bibliographicInformation.setYear(result.getChild("year").getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bibliographicInformation;
	}
	
	private static Document transformElement(Element source, String pathToXSL) throws IOException, TransformerException{
		StreamSource stylesource = new StreamSource(MonographTools.class.getClassLoader().getResourceAsStream(pathToXSL));
		LOGGER.info(stylesource.toString());
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(stylesource);
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        transformer.transform(new JDOMSource(source), result);
        String resultsString = writer.toString();
        LOGGER.info(resultsString);
        SAXBuilder builder = new SAXBuilder();
        try {
        Document resultDoc = builder.build(new StringReader(resultsString));
        return resultDoc;
        } catch (JDOMException jdome) {
        	return null;
        }
        
    }

}
