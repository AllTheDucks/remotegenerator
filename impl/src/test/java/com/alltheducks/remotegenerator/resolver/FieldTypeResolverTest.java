package com.alltheducks.remotegenerator.resolver;

import com.alltheducks.remotegenerator.example.types.ExampleGenericModel;
import com.alltheducks.remotegenerator.model.ConvertedType;
import com.alltheducks.remotegenerator.resolver.FieldTypeResolver;
import com.alltheducks.remotegenerator.translator.TypeTranslator;
import com.alltheducks.remotegenerator.example.conversiontypes.ExampleConversionTypes;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FieldTypeResolverTest {

    private FieldTypeResolver fieldTypeResolver;
    private TypeTranslator typeTranslator;

    @Before
    public void setUp() throws Exception {
        typeTranslator = mock(TypeTranslator.class);

        fieldTypeResolver = new FieldTypeResolver();
        fieldTypeResolver.setTypeTranslator(typeTranslator);
    }

    @Test
    public void testGetFieldType_withoutConversionTypeAnnotation_expectTranslatorResponse() throws Exception {
        ConvertedType convertedType = new ConvertedType();
        convertedType.setName("TranslatorResponse");

        Field field = ExampleConversionTypes.class.getDeclaredField("withoutConversionTypeAnnotation");

        when(typeTranslator.translate(field)).thenReturn(convertedType);

        assertEquals("TranslatorResponse", fieldTypeResolver.getFieldType(field).getName());
    }

    @Test
    public void testGetFieldType_withConversionTypeAnnotation_expectAnnotationValue() throws Exception {
        Field field = ExampleConversionTypes.class.getDeclaredField("withConversionTypeAnnotation");
        assertEquals("AnnotationValue", fieldTypeResolver.getFieldType(field).getName());
    }

    @Test
    public void testGetFieldType_withConversionTypeAnnotationAndRequires_expectTwoRequires() throws Exception {
        Field field = ExampleConversionTypes.class.getDeclaredField("withConversionTypeAnnotationAndRequires");
        ConvertedType convertedType = fieldTypeResolver.getFieldType(field);
        assertEquals("AnnotationValue", convertedType.getName());
        assertEquals(2, convertedType.getRequires().size());
        assertTrue("Contains 'RequiresOne'", convertedType.getRequires().contains("RequiresOne"));
        assertTrue("Contains 'RequiresTwo'", convertedType.getRequires().contains("RequiresTwo"));
    }

    @Test
    public void testGetFieldType_withGenericField_expectGenericType() throws Exception {
        Field field =  ExampleGenericModel.class.getDeclaredField("fieldT");
        ConvertedType convertedType = fieldTypeResolver.getFieldType(field);

        assertEquals("T", convertedType.getName());
    }
}
