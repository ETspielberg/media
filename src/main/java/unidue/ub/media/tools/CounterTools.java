package unidue.ub.media.tools;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unidue.ub.media.analysis.Counter;
import unidue.ub.media.analysis.DatabaseCounter;
import unidue.ub.media.analysis.EbookCounter;
import unidue.ub.media.analysis.JournalCounter;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Helpful tools for handling of COUNTER reports
 * @author Eike Spielberg
 *
 */
public class CounterTools {

    private static final Namespace namespaceSushiCounter = Namespace.getNamespace("http://www.niso.org/schemas/sushi/counter");

    private static final Namespace namespaceCounter = Namespace.getNamespace("http://www.niso.org/schemas/counter");

    private static final Namespace namespaceSOAP = Namespace.getNamespace("http://schemas.xmlsoap.org/soap/envelope/");

    public final static Logger log = LoggerFactory.getLogger(CounterTools.class);

    /**
     * returns a list of <code>Counter</code> objects generated from the response of a SUSHI request.
     * @param sushi the SUSHI response
     * @return counters the list of COUNTER reports
     * @exception SOAPException thrown upon errors occurring parsing the SUSHI response
     * @exception IOException thrown upon errors occurring writing of the SUSHI response to the SAX-Buuilder
     * @exception JDOMException thrown upon errors parsing the xml structure of the SUSHI response
     */
    public static List<? extends Counter> convertSOAPMessageToCounters(SOAPMessage sushi) throws SOAPException, IOException, JDOMException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        sushi.writeTo(out);
        String sushiString = new String(out.toByteArray());
        SAXBuilder builder = new SAXBuilder();
        Document sushiDoc = builder.build(new StringReader(sushiString));
        Element sushiElement = sushiDoc.detachRootElement().clone();
        try {
            Element report = sushiElement.getChild("Body", namespaceSOAP).getChild("ReportResponse", namespaceSushiCounter).getChild("Report", namespaceSushiCounter).getChild("Report", namespaceCounter);
            String type = report.getAttributeValue("Name");
            List<Element> reportItems = report.getChild("Customer", namespaceCounter).getChildren("ReportItems", namespaceCounter);
            switch (type) {
                case "JR1":
                    return convertCounterElementsToJournalCounters(reportItems);
                case "BR1":
                    return convertCounterElementsToEbookCounters(reportItems);
                case "BR2":
                    return convertCounterElementsToEbookCounters(reportItems);
                case "DR1":
                    return convertCounterElementsToDatabaseCounters(reportItems);
            }
        } catch (Exception e) {
            log.info("could not convert SOAP response: \n");
            log.info(sushiString);
        }
        return null;
    }

    public static List<DatabaseCounter> convertCounterElementsToDatabaseCounters(List<Element> reportItems) {
        List<DatabaseCounter> counters = new ArrayList<>();
        log.info("found usage data for " + reportItems.size() + " items.");
        for (Element item : reportItems) {
            String publisher = item.getChild("ItemPublisher", namespaceCounter).getValue();
            String platform = item.getChild("ItemPlatform", namespaceCounter).getValue();
            String title = item.getChild("ItemName", namespaceCounter).getValue();
            List<Element> itemPerformances = item.getChildren("ItemPerformance", namespaceCounter);
            log.info("found usage data for " + itemPerformances.size() + " time ranges.");
            for (Element itemPerformance : itemPerformances) {
                Element period = itemPerformance.getChild("Period", namespaceCounter);
                String startDate = period.getChild("Begin", namespaceCounter).getValue();
                List<Element> instances = itemPerformance.getChildren("Instance", namespaceCounter);
                int year = Integer.parseInt(startDate.substring(0, 4));
                int month = Integer.parseInt(startDate.substring(5, 7));
                log.info("reading metrics for " + month + "-" + year);
                DatabaseCounter counter = new DatabaseCounter(publisher,platform,month,year);
                counter.setTitle(title);

                for (Element instance : instances) {
                    long value = Long.parseLong(instance.getChild("Count", namespaceCounter).getValue().trim());
                    String metricType = instance.getChild("MetricType", namespaceCounter).getValue();
                    switch (metricType) {
                        case "record_view": {
                            counter.setRecordViews(value);
                            break;
                        }
                        case "result_click": {
                            counter.setResultClicks(value);
                            break;
                        }
                        case "search_reg": {
                            counter.setRegularSearches(value);
                            break;
                        }
                        case "search_fed": {
                            counter.setFederatedAndAutomatedSearches(value);
                            break;
                        }
                        default: {
                            log.info(metricType + " is not a categorized metric.");
                        }
                    }
                }
                counters.add(counter);

            }
        }
        log.info("read " + counters.size() + " database counter statistics from counter element.");
        return counters;
    }

    public static List<EbookCounter> convertCounterElementsToEbookCounters(List<Element> reportItems) {
        log.info("found usage data for " + reportItems.size() + " items.");
        List<EbookCounter> counters = new ArrayList<>();
        for (Element item : reportItems) {
            String publisher = item.getChild("ItemPublisher", namespaceCounter).getValue();
            String platform = item.getChild("ItemPlatform", namespaceCounter).getValue();
            String title = item.getChild("ItemName", namespaceCounter).getValue();
            List<Element> itemPerformances = item.getChildren("ItemPerformance", namespaceCounter);
            List<Element> identifiers = item.getChildren("ItemIdentifier", namespaceCounter);
            String onlineIsbn = "";
            String doi = "";
            String printIsbn = "";
            String isni = "";
            String proprietary = "";
            for (Element identifier : identifiers) {
                String identifierType = identifier.getChild("Type", namespaceCounter).getValue();
                String value = identifier.getChild("Value", namespaceCounter).getValue();
                switch (identifierType) {
                    case "Online_ISBN" : {
                        onlineIsbn = value;
                        break;
                    }
                    case "Print_ISBN" : {
                        printIsbn = value;
                        break;
                    }
                    case "ISNI" : {
                        isni = value;
                        break;
                    }
                    case "DOI" : {
                        doi = value;
                        break;
                    }
                    case "Proprietary" : {
                        proprietary = value;
                        break;
                    }default: {
                        log.info(identifierType + " is not a categorized identifier type.");
                    }
                }
            }
            for (Element itemPerformance : itemPerformances) {
                Element period = itemPerformance.getChild("Period", namespaceCounter);
                String startDate = period.getChild("Begin", namespaceCounter).getValue();
                List<Element> instances = itemPerformance.getChildren("Instance", namespaceCounter);
                int year = Integer.parseInt(startDate.substring(0, 4));
                int month = Integer.parseInt(startDate.substring(5, 7));
                EbookCounter counter = new EbookCounter(onlineIsbn,platform,month,year);
                counter.setDoi(doi).setProprietaryIdentifier(proprietary).setIsni(isni).setPrintIsbn(printIsbn).setPublisher(publisher).setTitle(title);
                for (Element instance : instances) {
                    long value = Long.parseLong(instance.getChild("Count", namespaceCounter).getValue().trim());
                    String metricType = instance.getChild("MetricType", namespaceCounter).getValue();
                    switch (metricType) {
                        case "ft_pdf": {
                            counter.setPdfRequests(value);
                            break;
                        }
                        case "ft_pdf_mobile": {
                            counter.setPdfRequestsMobile(value);
                            break;
                        }
                        case "ft_html": {
                            counter.setHtmlRequests(value);
                            break;
                        }
                        case "ft_html_mobile": {
                            counter.setHtmlRequestsMobile(value);
                            break;
                        }
                        case "ft_ps": {
                            counter.setPsRequests(value);
                            break;
                        }
                        case "ft_ps_mobile": {
                            counter.setPsRequestsMobile(value);
                            break;
                        }
                        case "ft_epub": {
                            counter.setEpubRequest(value);
                            break;
                        }
                        case "ft_total": {
                            counter.setTotalRequests(value);
                            break;
                        }
                        default: {
                            log.info(metricType + " is not a categorized metric.");
                        }
                    }
                }
                counters.add(counter);
            }
        }
        log.info("read " + counters.size() + " database counter statistics from counter element.");
        return counters;
    }

    public static List<JournalCounter> convertCounterElementsToJournalCounters(List<Element> reportItems) {
        log.info("found usage data for " + reportItems.size() + " items.");
        List<JournalCounter> counters = new ArrayList<>();
        for (Element item : reportItems) {
            String fullname = item.getChild("ItemName", namespaceCounter).getValue();
            log.info("reading data for " + fullname);
            String publisher = item.getChild("ItemPublisher", namespaceCounter).getValue();
            String platform = item.getChild("ItemPlatform", namespaceCounter).getValue();
            String type = item.getChild("ItemDataType", namespaceCounter).getValue();
            List<Element> identifiers = item.getChildren("ItemIdentifier", namespaceCounter);
            String onlineISSN = "";
            String printISSN = "";
            String proprietary = "";
            for (Element identifier : identifiers) {
                String identifierType = identifier.getChild("Type", namespaceCounter).getValue();
                String value = identifier.getChild("Value", namespaceCounter).getValue();
                switch (identifierType) {
                    case "Online_ISSN" :  {
                        onlineISSN = value;
                        break;
                    }
                    case "Print_ISSN" : {
                        printISSN = value;
                        break;
                    }
                    case "Proprietary" : {
                        proprietary = value;
                        break;
                    }
                    default: {
                        log.info(identifierType + " is not a categorized identifier type.");
                    }
                }
            }
            List<Element> itemPerformances = item.getChildren("ItemPerformance", namespaceCounter);
            for (Element itemPerformance : itemPerformances) {
                Element period = itemPerformance.getChild("Period", namespaceCounter);
                String startDate = period.getChild("Begin", namespaceCounter).getValue();
                List<Element> instances = itemPerformance.getChildren("Instance", namespaceCounter);
                int year = Integer.parseInt(startDate.substring(0, 4));
                int month = Integer.parseInt(startDate.substring(5, 7));
                log.info("reading metrics for " + month + "-" + year);
                JournalCounter counter = new JournalCounter(onlineISSN,platform,month,year);
                counter.setFullName(fullname).setType(type).setPrintIssn(printISSN).setAbbreviation(proprietary).setPublisher(publisher);

                for (Element instance : instances) {
                    long value = Long.parseLong(instance.getChild("Count", namespaceCounter).getValue().trim());
                    String metricType = instance.getChild("MetricType", namespaceCounter).getValue();
                    switch (metricType) {
                        case "ft_html": {
                            counter.setHtmlRequests(value);
                            break;
                        }
                        case "ft_html_mobile": {
                            counter.setHtmlRequestsMobile(value);
                            break;
                        }
                        case "ft_pdf": {
                            counter.setPdfRequests(value);
                            break;
                        }
                        case "ft_pdf_mobile": {
                            counter.setPdfRequestsMobile(value);
                            break;
                        }
                        case "ft_ps": {
                            counter.setPsRequests(value);
                            break;
                        }
                        case "ft_ps_mobile": {
                            counter.setPsRequestsMobile(value);
                            break;
                        }
                        case "ft_total": {
                            counter.setTotalRequests(value);
                            break;
                        }
                        default: {
                            log.info(metricType + " is not a categorized metric.");
                        }
                    }
                    counters.add(counter);
                    long totalRequests = counter.getHtmlRequests() + counter.getHtmlRequestsMobile() + counter.getPdfRequests() + counter.getPdfRequestsMobile() + counter.getPsRequests() + counter.getPsRequestsMobile();
                    if (counter.getTotalRequests() != totalRequests)
                        log.info("sum of individual requests does not match total requests!");
                }
            }
        }
        log.info("read " + counters.size() + " counter statistics from counter element.");
        return counters;
    }

}
