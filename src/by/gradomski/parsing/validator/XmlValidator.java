package by.gradomski.parsing.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    private static Logger logger = LogManager.getLogger();

    public boolean validateXmlFile(String xmlPath, String xsdPath){
        File xmlFile = new File(xmlPath);
        if(!xmlFile.exists()){
            logger.error("XML file not found");
            return false;
        }
        File xsdFile = new File(xsdPath);
        if(!xsdFile.exists()){
            logger.error("XSD file not found");
            return false;
        }
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        try {
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlPath));
            logger.info("Validation correct");
            return true;
        } catch (SAXException e) {
            logger.fatal("IOException");
            e.printStackTrace();
            return false;
        } catch (IOException eIO){
            logger.fatal("IOException");
            return false;
        }
    }
}
