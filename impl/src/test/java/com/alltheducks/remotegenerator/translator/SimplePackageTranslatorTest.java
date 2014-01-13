package com.alltheducks.remotegenerator.translator;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimplePackageTranslatorTest {

    @Test
    public void testTranslate_withNoPackage_expectNothingToChange() throws Exception {
        SimplePackageTranslator simplePackageTranslator = new SimplePackageTranslator();
        simplePackageTranslator.addTranslation("com.alltheducks.remotegenerator", "atd.jmtc");

        String result = simplePackageTranslator.translate("TestClass");
        assertEquals("TestClass", result);
    }

    @Test
    public void testTranslate_withDifferentPackage_expectNothingToChange() throws Exception {
        SimplePackageTranslator simplePackageTranslator = new SimplePackageTranslator();
        simplePackageTranslator.addTranslation("com.alltheducks.remotegenerator", "atd.jmtc");

        String result = simplePackageTranslator.translate("com.example.test.TestClass");
        assertEquals("com.example.test.TestClass", result);
    }

    @Test
    public void testTranslate_withMultipleTranslations_expectCorrectTranslations() throws Exception {
        SimplePackageTranslator simplePackageTranslator = new SimplePackageTranslator();
        simplePackageTranslator.addTranslation("com.alltheducks.remotegenerator", "atd.jmtc");
        simplePackageTranslator.addTranslation("com.example.test", "ex.test");

        String result = simplePackageTranslator.translate("com.alltheducks.remotegenerator.TestClass");
        assertEquals("atd.jmtc.TestClass", result);

        result = simplePackageTranslator.translate("com.example.test.TestClass");
        assertEquals("ex.test.TestClass", result);
    }

    @Test
    public void testTranslate_withPackage_expectTranslation() throws Exception {
        SimplePackageTranslator simplePackageTranslator = new SimplePackageTranslator();
        simplePackageTranslator.addTranslation("com.alltheducks.remotegenerator", "atd.jmtc");

        String result = simplePackageTranslator.translate("com.alltheducks.remotegenerator.TestClass");
        assertEquals("atd.jmtc.TestClass", result);
    }

}
