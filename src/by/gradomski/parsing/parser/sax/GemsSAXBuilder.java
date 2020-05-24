package by.gradomski.parsing.parser.sax;

import by.gradomski.parsing.entity.Gem;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GemsSAXBuilder {
    private static Logger logger = LogManager.getLogger();
    private Set<Gem> gemSet;
    private GemsHandler gemsHandler;
    private SAXParser parser;

    public GemsSAXBuilder(){
        gemsHandler = new GemsHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            logger.fatal("ParserConfigurationException: ");
            e.printStackTrace();
        } catch (SAXException e) {
            logger.fatal("SAXException: ");
            e.printStackTrace();
        }
    }

    public Set<Gem> getGemSet(){
        return gemSet;
    }

    public void buildSetGems(String fileName){
        try{
            parser.parse(fileName, gemsHandler);
            gemSet = gemsHandler.getGemSet();
        } catch (SAXException e) {
            logger.fatal("SAXException: ");
            e.printStackTrace();
        } catch (IOException e){
            logger.fatal("IOException");
        }
    }
}
