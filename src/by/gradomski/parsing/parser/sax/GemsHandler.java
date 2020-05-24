package by.gradomski.parsing.parser.sax;

import by.gradomski.parsing.entity.Gem;
import by.gradomski.parsing.entity.VisualParameters;
import by.gradomski.parsing.exception.IncorrectGemTypeException;
import by.gradomski.parsing.parser.GemType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class GemsHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();
    private Set<Gem> gemSet;
    private GemType currentType;
    private Gem currentGem;
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public GemsHandler(){
        gemSet = new LinkedHashSet<Gem>();
    }

    public Set<Gem> getGemSet(){
        return gemSet;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        logger.debug("qName = " + qName);
        if ("gem".equals(qName)){
            currentGem = new Gem();
            for(int i=0; i < attributes.getLength(); i++){
                logger.debug("attribute " + i + ": " + attributes.getLocalName(i));
                String attribute = attributes.getLocalName(i);
                switch (attribute){
                    case "id":
                        currentGem.setId(attributes.getValue(i));
                        logger.info("currentGem id=" + currentGem.getId());
                        break;
                    case "name":
                        currentGem.setName(attributes.getValue(i));
                        logger.info("currentGem name=" + currentGem.getName());
                        break;
                    case "value":
                        int value = Integer.parseInt(attributes.getValue(i));
                        currentGem.setValue(value);
                        logger.info("currentGem value=" + currentGem.getValue());
                        break;
                }
            }
        } else {
            try {
                currentType = GemType.getTypeByValue(qName);
            }catch (IncorrectGemTypeException e){
                logger.error("IncorrectGemTypeException: " + qName);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch, start, length).trim();
        if (currentType != null){
            switch (currentType) {
                case PRECIOUSNESS:
                    currentGem.setPreciousness(text);
                    break;
                case ORIGIN:
                    currentGem.setOrigin(text);
                    break;
                case CUTTING_DATE:
                    try {
                        Date date = DATE_FORMAT.parse(text);
                        currentGem.setCuttingDate(date);
                    } catch (ParseException e) {
                        logger.error("date parsing: ParseException");
                        e.printStackTrace();
                    }
                    break;
                case VISUAL_PARAMETERS:
                    VisualParameters visualParameters = new VisualParameters();
                    currentGem.setVisualParameters(visualParameters);
                    break;
                case COLOR:
                    currentGem.getVisualParameters().setColor(text);
                    break;
                case CLARITY:
                    currentGem.getVisualParameters().setClarity(Integer.parseInt(text));
                    break;
                case FACETS:
                    currentGem.getVisualParameters().setFacets(Integer.parseInt(text));
                    break;
            }
            currentType = null;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("gem".equals(qName)){
            gemSet.add(currentGem);
            logger.debug("end Element, element added");
        }
    }
}
