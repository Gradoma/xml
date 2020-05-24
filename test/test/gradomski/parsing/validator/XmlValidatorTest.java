package test.gradomski.parsing.validator;

import by.gradomski.parsing.validator.XmlValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class XmlValidatorTest {
    XmlValidator validator;

    @BeforeMethod
    public void setUp() {
        validator = new XmlValidator();
    }

    @Test(groups = "Validation")
    public void testValidateXmlFile() {
        String testFileName = "testresources/testfile/test.xml";
        Assert.assertTrue(validator.validateXmlFile(testFileName));
    }

    @Test(groups = "Validation")
    public void testValidateXmlFileNotFound() {
        String testFileName = "testresources/testfile/noFile.xml";
        Assert.assertFalse(validator.validateXmlFile(testFileName));
    }

    @Test(groups = "Validation")
    public void testValidateXmlFileIncorrect() {
        String testFileName = "testresources/testfile/incorrect.xml";
        Assert.assertFalse(validator.validateXmlFile(testFileName));
    }
}