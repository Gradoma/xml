package by.gradomski.parsing.parser.stax;

import by.gradomski.parsing.entity.Gem;
import by.gradomski.parsing.entity.VisualParameters;
import by.gradomski.parsing.exception.IncorrectGemTypeException;
import by.gradomski.parsing.parser.GemType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class GemsStAXBuilder {
    private static Logger logger = LogManager.getLogger();
    private Set<Gem> gemSet;
    private XMLInputFactory factory;
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public GemsStAXBuilder(){
        gemSet = new LinkedHashSet<>();
        factory = XMLInputFactory.newInstance();
    }

    public Set<Gem> getGemSet(){
        return gemSet;
    }

    public void buildSetGems(String fileName){
        XMLStreamReader reader;
        String elementName;
        try (FileInputStream inputStream = new FileInputStream(fileName)){
            reader = factory.createXMLStreamReader(inputStream);
            while (reader.hasNext()){
                int i = reader.next();
                logger.info("i = " + i);
                if (i == XMLStreamConstants.START_ELEMENT){
                    elementName = reader.getLocalName();
                    if(GemType.getTypeByValue(elementName) == GemType.GEM){
                        Gem gem = buildGem(reader);
                        gemSet.add(gem);
                    }
                }
            }
        } catch (IOException e){
            logger.error("IOException: problem with file " + fileName);
        }catch (XMLStreamException sEx){
            logger.error("XMLStreamException: problem while createXMLStreamReader or parsing");
        } catch (IncorrectGemTypeException e){
            logger.error("IncorrectGemTypeException: " + e.getMessage());
        }
    }

    private Gem buildGem(XMLStreamReader reader) throws XMLStreamException, IncorrectGemTypeException{
        Gem gem = new Gem();
        for(int i=0; i < reader.getAttributeCount(); i++){
            String attribute = reader.getAttributeLocalName(i);
            logger.error("attribute:" + attribute);
            switch (attribute){
                case "id":
                    gem.setId(reader.getAttributeValue(i));
                    break;
                case "name":
                    gem.setName(reader.getAttributeValue(i));
                    break;
                case "value":
                    int value = Integer.parseInt(reader.getAttributeValue(i));
                    gem.setValue(value);
                    break;
            }
        }
        String name;
        while (reader.hasNext()){
            int i = reader.next();
            switch (i){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemType.getTypeByValue(name)) {
                        case PRECIOUSNESS:
                            gem.setPreciousness(getText(reader));
                            break;
                        case ORIGIN:
                            gem.setOrigin(getText(reader));
                            break;
                        case CUTTING_DATE:
                            String dateString = getText(reader);
                            try{
                                Date date = DATE_FORMAT.parse(dateString);
                                gem.setCuttingDate(date);
                            } catch (ParseException e){
                                logger.error("date parsing: ParseException");
                            }
                            break;
                        case VISUAL_PARAMETERS:
                            gem.setVisualParameters(getVisualParameters(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemType.getTypeByValue(name) == GemType.GEM){
                        return gem;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Incorrect element in <gem>");
    }

    private String getText(XMLStreamReader reader) throws XMLStreamException{
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private VisualParameters getVisualParameters(XMLStreamReader reader) throws XMLStreamException, IncorrectGemTypeException{
        VisualParameters visualParameters = new VisualParameters();
        int i;
        String name;
        while (reader.hasNext()){
            i = reader.next();
            switch (i){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemType.getTypeByValue(name)) {
                        case COLOR:
                            visualParameters.setColor(getText(reader));
                            break;
                        case CLARITY:
                            int clarity = Integer.parseInt(getText(reader));
                            visualParameters.setClarity(clarity);
                            break;
                        case FACETS:
                            int facets = Integer.parseInt(getText(reader));
                            visualParameters.setFacets(facets);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemType.getTypeByValue(name) == GemType.VISUAL_PARAMETERS){
                        return visualParameters;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Incorrect element in <visual-parameters>");
    }
}
