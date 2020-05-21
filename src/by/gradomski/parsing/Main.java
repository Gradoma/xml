package by.gradomski.parsing;

import by.gradomski.parsing.validator.XmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static void main(String[] args) {
        String xml = "resources/docs/gems";
        String xsd = "resources/docs/gems_schema.xsd";

        XmlValidator validator = new XmlValidator();
        validator.validateXmlFile(xml, xsd);
    }
}
