package com.alltheducks.javamodeltoclosure;

import com.alltheducks.javamodeltoclosure.example.oneannotatedclass.Example;
import com.alltheducks.javamodeltoclosure.model.ConvertedField;
import com.alltheducks.javamodeltoclosure.model.ConvertedType;
import com.alltheducks.javamodeltoclosure.resolver.FieldTypeResolver;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConversionFieldServiceTest {

    private ConversionFieldService conversionFieldService;
    private FieldTypeResolver fieldTypeResolver;

    @Before
    public void setUp() {
        fieldTypeResolver = mock(FieldTypeResolver.class);

        conversionFieldService = new ConversionFieldService();
        conversionFieldService.setFieldTypeResolver(fieldTypeResolver);
    }

    @Test
    public void testGetConversionField() throws Exception {
        ConvertedType convertedType = new ConvertedType();
        convertedType.setName("string");

        Field field = Example.class.getDeclaredField("aString");
        when(fieldTypeResolver.getFieldType(field)).thenReturn(convertedType);

        ConvertedField convertedField = conversionFieldService.getConversionField(field);
        assertEquals("aString", convertedField.getName());
        assertEquals("string", convertedField.getType().getName());
    }
}
