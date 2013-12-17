package com.alltheducks.javamodeltoclosure.closure;

import com.alltheducks.javamodeltoclosure.example.types.ExampleGenericModel;
import com.alltheducks.javamodeltoclosure.example.types.ExampleSimpleModel;
import com.alltheducks.javamodeltoclosure.example.types.ExampleWithComplexTypes;
import com.alltheducks.javamodeltoclosure.example.types.ExampleWithSimpleTypes;
import com.alltheducks.javamodeltoclosure.model.ConvertedField;
import com.alltheducks.javamodeltoclosure.model.ConvertedType;
import com.google.common.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ClosureTypeTranslatorTest {

    ClosureTypeTranslator translator;

    @Before
    public void setUp() {
        translator = new ClosureTypeTranslator();
    }

    @Test
    public void testTranslate_withInteger_expectNumber() throws Exception {
        Field field = ExampleWithSimpleTypes.class.getDeclaredField("anInteger");
        assertEquals("number", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withPrimitiveInt_expectNumber() throws Exception {
        Field field = ExampleWithSimpleTypes.class.getDeclaredField("anInt");
        assertEquals("number", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withPrimitiveChar_expectString() throws Exception {
        Field field = ExampleWithSimpleTypes.class.getDeclaredField("aChar");
        assertEquals("string", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withPrimitiveBoolean_expectBoolean() throws Exception {
        Field field = ExampleWithSimpleTypes.class.getDeclaredField("aBoolean");
        assertEquals("boolean", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withArrayOfStrings_expectArrayOfStrings() throws Exception {
        Field field = ExampleWithSimpleTypes.class.getDeclaredField("aStringArray");
        assertEquals("Array.<string>", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withArrayOfIntegers_expectArrayOfNumbers() throws Exception {
        Field field = ExampleWithSimpleTypes.class.getDeclaredField("anIntegerArray");
        assertEquals("Array.<number>", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withString_expectString() throws Exception {
        Field field = ExampleWithSimpleTypes.class.getDeclaredField("aString");
        assertEquals("string", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withListOfStrings_expectListOfStrings() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("listOfStrings");
        assertEquals("List.<string>", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withArrayListOfStrings_expectListOfStrings() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("arrayListOfStrings");
        assertEquals("List.<string>", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withHashMapOfIntegerKeysAndStringValues_expectMapOfNumberKeysAndStringValues() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("hashMapOfIntegerKeysAndStringValues");
        assertEquals("Map.<number,string>", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withHashMapOfStringKeysAndArrayListOfStringValues_expectMapOfStringKeysAndListOfStringValues() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("hashMapOfStringKeysAndArrayListOfStringValues");
        assertEquals("Map.<string,List.<string>>", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withListOfStringsArrays_expectMapOfStringKeysAndListOfStringValues() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("listOfStringsArrays");
        assertEquals("Array.<List.<string>>", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withExampleSimpleModel_expectExampleSimpleModel() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("exampleSimpleModel");

        Set<TypeToken<?>> packageTypes = new HashSet<TypeToken<?>>();
        packageTypes.add(TypeToken.of(ExampleSimpleModel.class));
        translator.setPackageTypes(packageTypes);

        assertEquals("ExampleSimpleModel", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withListOfExampleSimpleModel_expectListOfExampleSimpleModel() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("listOfExampleSimpleModels");

        Set<TypeToken<?>> packageTypes = new HashSet<TypeToken<?>>();
        packageTypes.add(TypeToken.of(ExampleSimpleModel.class));
        translator.setPackageTypes(packageTypes);

        assertEquals("List.<ExampleSimpleModel>", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withExampleGenericModelWithStringTypeParameters_expectExampleGenericModelWithStringTypeParameters() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("exampleGenericModelWithStringTypeParameters");

        Set<TypeToken<?>> packageTypes = new HashSet<TypeToken<?>>();
        packageTypes.add(TypeToken.of(ExampleGenericModel.class));
        translator.setPackageTypes(packageTypes);

        assertEquals("ExampleGenericModel.<string,string,string>", translator.translate(field).getName());
    }

    @Test
    public void testTranslate_withExampleGenericModelWithGenericTypeParameters_expectExampleGenericModelWithGenericTypeParameters() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("exampleGenericModelWithGenericTypeParameters");

        Set<TypeToken<?>> packageTypes = new HashSet<TypeToken<?>>();
        packageTypes.add(TypeToken.of(ExampleGenericModel.class));
        packageTypes.add(TypeToken.of(ExampleSimpleModel.class));
        translator.setPackageTypes(packageTypes);

        ConvertedType convertedType = translator.translate(field);

        assertEquals("ExampleGenericModel.<ExampleSimpleModel,List.<goog.date.DateTime>,Map.<number,Array.<number>>>", convertedType.getName());
        assertEquals(3, convertedType.getRequires().size());
        assertTrue("Contains 'ExampleGenericModel'", convertedType.getRequires().contains("ExampleGenericModel"));
        assertTrue("Contains 'ExampleSimpleModel'", convertedType.getRequires().contains("ExampleSimpleModel"));
        assertTrue("Contains 'goog.date.DateTime'", convertedType.getRequires().contains("goog.date.DateTime"));
    }
}
