package by.gradomski.parsing.parser.dom;

import by.gradomski.parsing.entity.Gem;
import by.gradomski.parsing.entity.VisualParameters;
import by.gradomski.parsing.exception.ContentNameNotPresentException;
import by.gradomski.parsing.exception.GemBuildingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class GemsDOMBuilder {
    private static Logger logger = LogManager.getLogger();
    private Set<Gem> gemSet;
    private DocumentBuilder documentBuilder;
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public GemsDOMBuilder(){
        gemSet = new LinkedHashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.fatal("ParserConfigurationException: try to get DocumentBuilder");
            e.printStackTrace();
        }
    }

    public Set<Gem> getGemSet(){
        return gemSet;
    }

    public void buildGemSet(String fileName){
        Document document = null;
        try {
            document = documentBuilder.parse(fileName);
        } catch (SAXException e) {
            logger.error("SAXException: parsing failed");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IOException: file problem");
            e.printStackTrace();
        }
        Element root = document.getDocumentElement();
        NodeList list = root.getElementsByTagName("gem");
        for (int i=0; i < list.getLength(); i++){
            Element elementGem = (Element) list.item(i);
            try {
                Gem gem = buildGem(elementGem);
                gemSet.add(gem);
            } catch (GemBuildingException e){
                logger.error("Gem building problem: " + e.getMessage());
            }
        }
    }

    private Gem buildGem(Element elementGem) throws GemBuildingException {
        Gem gem = new Gem();
        gem.setId(elementGem.getAttribute("id"));
        gem.setName(elementGem.getAttribute("name"));
        if (elementGem.hasAttribute("value")){
            int value = Integer.parseInt(elementGem.getAttribute("value"));
            gem.setValue(value);
        }
        try{
            gem.setPreciousness(getTextFromElement(elementGem, "preciousness"));
            gem.setOrigin(getTextFromElement(elementGem, "origin"));
            String dateString = getTextFromElement(elementGem, "cutting-date");
            Date date = DATE_FORMAT.parse(dateString);
            gem.setCuttingDate(date);
            VisualParameters visualParameters = new VisualParameters();
            Element elementVisualParam = (Element) elementGem.getElementsByTagName("visual-parameters").item(0);
            visualParameters.setColor(getTextFromElement(elementVisualParam, "color"));
            int facets = Integer.parseInt(getTextFromElement(elementVisualParam, "facets"));
            visualParameters.setFacets(facets);
            int clarity = Integer.parseInt(getTextFromElement(elementVisualParam, "clarity"));
            visualParameters.setClarity(clarity);
            gem.setVisualParameters(visualParameters);
            return gem;
        } catch (ContentNameNotPresentException e){
            throw new GemBuildingException("ContentNameNotPresentException: " + e.getMessage());
        } catch (ParseException pEx){
            throw new GemBuildingException("ParseException: " + pEx.getMessage());
        }

    }

    private String getTextFromElement(Element element, String name) throws ContentNameNotPresentException{
        NodeList list = element.getElementsByTagName(name);
        if(list.getLength() == 0){
            throw new ContentNameNotPresentException("can't find name " + name);
        }
        Node node = list.item(0);
        return node.getTextContent();
    }
}
