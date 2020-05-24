package test.gradomski.parsing.parser.dom;

import by.gradomski.parsing.entity.Gem;
import by.gradomski.parsing.entity.VisualParameters;
import by.gradomski.parsing.parser.dom.GemsDOMBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.testng.Assert.*;

public class GemsDOMBuilderTest {

    @Test (dependsOnGroups = "Validation")
    public void testBuildGemSet() {
        Set<Gem> set = new LinkedHashSet<>();
        Gem gem = new Gem();
        gem.setId("Di1");
        gem.setName("Diamond");
        gem.setValue(15);
        gem.setOrigin("Argentina");
        gem.setPreciousness("gemstone");
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2003, Calendar.OCTOBER, 15);
        gem.setCuttingDate(calendar.getTime());
        VisualParameters visualParameters = new VisualParameters();
        visualParameters.setColor("colorless");
        visualParameters.setClarity(90);
        visualParameters.setFacets(57);
        gem.setVisualParameters(visualParameters);
        set.add(gem);
        GemsDOMBuilder builder = new GemsDOMBuilder();
        builder.buildGemSet("testresources/testfile/test.xml");
        Assert.assertEquals(set, builder.getGemSet());
//        set = builder.getGemSet();
//        for (Iterator<Gem> it = set.iterator(); it.hasNext(); ) {
//            Gem g = it.next();
//            Assert.assertEquals(g, gem);
//        }
    }
}