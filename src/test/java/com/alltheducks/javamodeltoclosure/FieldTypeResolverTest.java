package com.alltheducks.javamodeltoclosure;

import com.alltheducks.javamodeltoclosure.model.ConvertedType;
import com.alltheducks.javamodeltoclosure.resolver.FieldTypeResolver;
import com.alltheducks.javamodeltoclosure.translator.TypeTranslator;
import com.alltheducks.javamodeltoclosure.example.conversiontypes.ExampleConversionTypes;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: shane
 * Date: 17/12/13
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
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
}
