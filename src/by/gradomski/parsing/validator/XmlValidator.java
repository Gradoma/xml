package by.gradomski.parsing.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    private static Logger logger = LogManager.getLogger();

    public boolean validateXmlFile(String xmlPath){
        File xmlFile = new File(xmlPath);
        if(!xmlFile.exists()){
            logger.error("XML file not found");
            return false;
        }
        String language = "http://www.w3.org/2001/XMLSchema";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            Schema schema = factory.newSchema();
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlPath);
            validator.validate(source);
            logger.info("Validation correct");
            return true;
        } catch (SAXException e) {
            logger.fatal("SAXException");
            e.printStackTrace();
            return false;
        } catch (IOException eIO){
            logger.fatal("IOException");
            return false;
        }
    }
}
