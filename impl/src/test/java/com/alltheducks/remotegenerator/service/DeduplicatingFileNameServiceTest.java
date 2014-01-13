package com.alltheducks.remotegenerator.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeduplicatingFileNameServiceTest {

    DeduplicatingFileNameService deduplicatingFileNameService;
    FileNameService fileNameService;

    @Before
    public void setup() {
        fileNameService = mock(FileNameService.class);
        when(fileNameService.getFileNameForClassWithoutExtension("TestClass")).thenReturn("TestClass");
        when(fileNameService.getFileNameForClassWithoutExtension("com.alltheducks.remotegenerator.TestClass")).thenReturn("TestClass");
        when(fileNameService.getFileNameForClassWithoutExtension("com.anotherexample.TestClass")).thenReturn("TestClass");
        when(fileNameService.getFileNameForClassWithoutExtension("com.example.TestClass")).thenReturn("TestClass");

        deduplicatingFileNameService = new DeduplicatingFileNameService();
        deduplicatingFileNameService.setFileNameService(fileNameService);
    }

    @Test
    public void testGetFileNameForClassWithoutExtension_withoutDuplicate_expectTranslation() throws Exception {
        String result = deduplicatingFileNameService.getFileNameForClassWithoutExtension("TestClass");
        assertEquals("TestClass", result);
    }

    @Test
    public void testGetFileNameForClassWithoutExtension_withDuplicate_expectTranslation() throws Exception {
        deduplicatingFileNameService.getFileNameForClassWithoutExtension("com.alltheducks.remotegenerator.TestClass");
        String result = deduplicatingFileNameService.getFileNameForClassWithoutExtension("com.example.TestClass");
        assertEquals("TestClass(1)", result);
    }

    @Test
    public void testGetFileNameForClassWithoutExtension_withThreeDuplicates_expectTranslation() throws Exception {
        deduplicatingFileNameService.getFileNameForClassWithoutExtension("com.alltheducks.remotegenerator.TestClass");
        deduplicatingFileNameService.getFileNameForClassWithoutExtension("com.anotherexample.TestClass");
        String result = deduplicatingFileNameService.getFileNameForClassWithoutExtension("com.example.TestClass");
        assertEquals("TestClass(2)", result);
    }
}
