package com.alltheducks.remotegenerator.service;

import com.alltheducks.remotegenerator.example.oneannotatedclass.Example;
import com.alltheducks.remotegenerator.example.types.ExampleGenericModel;
import com.alltheducks.remotegenerator.model.ConvertedField;
import com.alltheducks.remotegenerator.model.ConvertedType;
import com.alltheducks.remotegenerator.resolver.FieldTypeResolver;
import com.alltheducks.remotegenerator.service.ConvertedFieldService;
import com.google.common.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConvertedFieldServiceTest {

    private ConvertedFieldService conversionFieldService;
    private FieldTypeResolver fieldTypeResolver;

    @Before
    public void setUp() {
        fieldTypeResolver = mock(FieldTypeResolver.class);

        conversionFieldService = new ConvertedFieldService();
        conversionFieldService.setFieldTypeResolver(fieldTypeResolver);
    }

    @Test
    public void testGetConversionField() throws Exception {
        ConvertedType convertedType = new ConvertedType();
        convertedType.setName("string");

        Field field = Example.class.getDeclaredField("aString");
        when(fieldTypeResolver.getFieldType(field, new HashSet<TypeToken<?>>())).thenReturn(convertedType);

        ConvertedField convertedField = conversionFieldService.getConvertedField(field, new HashSet<TypeToken<?>>());
        assertEquals("aString", convertedField.getName());
        assertEquals("string", convertedField.getType().getName());
    }
}
