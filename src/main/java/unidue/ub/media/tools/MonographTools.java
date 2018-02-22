package unidue.ub.media.tools;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
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
import unidue.ub.media.analysis.Nrequests;
import unidue.ub.media.monographs.BibliographicInformation;
import unidue.ub.media.monographs.Event;
import unidue.ub.media.monographs.Item;
import unidue.ub.media.monographs.Manifestation;

public class MonographTools {

    private final static int LEVENTHRESHOLD = 100;

    private final static long daysInMillis = 1000L * 60L * 60L * 24L;

    /**
     * returns the Levenshtein distance comparing the authors and title of two
     * publications
     *
     * @param other other <code>Publication</code>-object to be compared to
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
        return authorLeven + titleLeven;
    }

    /**
     * creates a new <code>Publication</code>-object from a document
     *
     * @param mabxml the MAB-XML data as JDOM Element
     */
    public static BibliographicInformation buildBibligraphicInformationFromMABXML(Element mabxml) {
        BibliographicInformation bibliographicInformation = new BibliographicInformation();
        HashSet<String> keywordsSet = new HashSet<>();
        Element result = new Element("bibliographicInformation");
        try {
            result = transformElement(mabxml).detachRootElement().clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Element> authors = result.getChild("authors").getChildren();
        if (authors.size() > 0) {
            for (Element author : authors) {
                bibliographicInformation.addAuthor(author.getValue());
            }
        }

        List<Element> keywords = result.getChild("keywords").getChildren();
        if (keywords.size() > 0) {
            for (Element keyword : keywords) {
                String keywordText = keyword.getText();
                if (!keywordsSet.contains(keywordText) && !keywordText.isEmpty()) {
                    bibliographicInformation.addKeyword(keyword.getText());
                    keywordsSet.add(keywordText);
                }
            }
        }
        bibliographicInformation.setIsbn(getField(result, "isbn"));
        bibliographicInformation.setDoi(getField(result, "doi"));
        bibliographicInformation.setEdition(getField(result, "edition"));
        bibliographicInformation.setPlace(getField(result, "place"));
        bibliographicInformation.setPublisher(getField(result, "publisher"));
        bibliographicInformation.setSeries(getField(result, "series"));
        bibliographicInformation.setSubtitle(getField(result, "subtitle"));
        bibliographicInformation.setTitle(getField(result, "title"));
        bibliographicInformation.setYear(getField(result, "year"));
        bibliographicInformation.setAlephIdnetifier(getField(result, "alephIdentifier"));

        return bibliographicInformation;
    }

    private static String getField(Element result, String field) {
        if (result.getChild(field) != null) {
            return result.getChild(field).getValue();
        } else return "";
    }


    private static Document transformElement(Element source) throws IOException, TransformerException {
        StreamSource stylesource = new StreamSource(MonographTools.class.getClassLoader().getResourceAsStream("xsl/mabxml2bibliographicInformation.xsl"));
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(stylesource);
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        transformer.transform(new JDOMSource(source), result);
        String resultsString = writer.toString();
        SAXBuilder builder = new SAXBuilder();
        try {
            return builder.build(new StringReader(resultsString));
        } catch (JDOMException jdome) {
            return null;
        }

    }

    public static Nrequests getNrequestsFor(Manifestation manifestation, String statusLendable) {
        Nrequests nrequests = new Nrequests();
        for (Item item : manifestation.getItems()) {
            nrequests.NItems++;
            if (statusLendable.contains(item.getItemStatus())) {
                nrequests.NLendable++;
            }
        }
        for (Event event : manifestation.getEvents()) {
            if (event.getEndEvent() != null)
                continue;
            if (event.getType().equals("loan"))
                nrequests.NLoans++;
            if (event.getType().equals("request")) {
                nrequests.NRequests++;
                nrequests.totalDuration += (long) (((double) (System.currentTimeMillis() - event.getTime())) / (double) daysInMillis);
            }
        }
        if (nrequests.NRequests > 0) {
            nrequests.setDate(new Date());
            nrequests.setTitleId(manifestation.getTitleID());
            nrequests.setShelfmark(manifestation.getShelfmark());
            nrequests.setMab(manifestation.getBibliographicInformation().toString());
            nrequests.updateRatio();
            return nrequests;
        } else
            return null;
    }
}
