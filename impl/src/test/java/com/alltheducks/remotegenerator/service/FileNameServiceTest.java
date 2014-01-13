package com.alltheducks.remotegenerator.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FileNameServiceTest {

    FileNameService fileNameService;

    @Before
    public void setup() {
        fileNameService = mock(FileNameService.class, Mockito.CALLS_REAL_METHODS);
        doReturn("TestClass").when(fileNameService).getFileNameForClassWithoutExtension("TestClass");
    }

    @Test
    public void testGetFileNameForClass_withoutExtension_returnWithoutExtension() throws Exception {
        String result = fileNameService.getFileNameForClass("TestClass");
        assertEquals("TestClass", result);
    }

    @Test
    public void testGetFileNameForClass_withExtension_returnWithExtension() throws Exception {
        fileNameService.setExtension("js");

        String result = fileNameService.getFileNameForClass("TestClass");
        assertEquals("TestClass.js", result);
    }
}
