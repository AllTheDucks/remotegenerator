package com.alltheducks.remotegenerator.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class LowerCaseFileNameServiceTest {

    @Test
    public void testGetFileNameForClassWithoutExtension_withoutPackage_expectLowerCaseName() throws Exception {
        LowerCaseFileNameService lowerCaseFileNameService = new LowerCaseFileNameService();
        String result = lowerCaseFileNameService.getFileNameForClassWithoutExtension("TestClass");

        assertEquals("testclass", result);
    }

    @Test
    public void testGetFileNameForClassWithoutExtension_withPackage_expectLowerCaseName() throws Exception {
        LowerCaseFileNameService lowerCaseFileNameService = new LowerCaseFileNameService();
        String result = lowerCaseFileNameService.getFileNameForClassWithoutExtension("com.alltheducks.remotegenerator.TestClass");

        assertEquals("testclass", result);
    }

}
