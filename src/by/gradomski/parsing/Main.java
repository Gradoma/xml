package by.gradomski.parsing;

import by.gradomski.parsing.entity.Gem;
import by.gradomski.parsing.parser.sax.GemsSAXBuilder;
import by.gradomski.parsing.validator.XmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String xml = "resources/docs/gems";

        XmlValidator validator = new XmlValidator();
        validator.validateXmlFile(xml);

        GemsSAXBuilder builder = new GemsSAXBuilder();
        builder.buildSetGems("resources/docs/gems");
        Set<Gem> resultSet = builder.getGemSet();
        for (Gem current : resultSet){
            System.out.println(current);
        }
    }
}
