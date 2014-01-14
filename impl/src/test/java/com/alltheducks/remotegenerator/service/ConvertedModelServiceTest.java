package com.alltheducks.remotegenerator.service;

import com.alltheducks.remotegenerator.example.oneannotatedclass.Example;
import com.alltheducks.remotegenerator.model.ConvertedField;
import com.alltheducks.remotegenerator.model.ConvertedModel;
import com.alltheducks.remotegenerator.model.ConvertedType;
import com.alltheducks.remotegenerator.service.ConvertedFieldService;
import com.alltheducks.remotegenerator.service.ConvertedModelService;
import com.alltheducks.remotegenerator.translator.SimplePackageTranslator;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConvertedModelServiceTest {

    ConvertedModelService convertedModelService;
    ConvertedFieldService convertedFieldService;

    @Before
    public void setUp() {
        convertedFieldService = mock(ConvertedFieldService.class);

        convertedModelService = new ConvertedModelService();
        convertedModelService.setConvertedFieldService(convertedFieldService);
        convertedModelService.setPackageTranslator(new SimplePackageTranslator());
    }

    @Test
    public void testGetConvertedModel_withSimpleExample_expectConversion() throws Exception {
        ConvertedType convertedType = new ConvertedType();
        convertedType.setName("String");

        ConvertedField convertedField = new ConvertedField();
        convertedField.setName("aString");
        convertedField.setType(convertedType);

        Set<ConvertedField> convertedFields = new HashSet<ConvertedField>();
        convertedFields.add(convertedField);

        when(convertedFieldService.getAllConvertedFields(Example.class)).thenReturn(convertedFields);

        ConvertedModel convertedModel = convertedModelService.getConvertedModel(Example.class);
        assertEquals("com.alltheducks.remotegenerator.example.oneannotatedclass.Example", convertedModel.getName());
        assertEquals(convertedFields, convertedModel.getConvertedFields());
    }

    @Test void testGetConvertedMode_withGenericExample_expectConversion() throws Exception {

    }
}
