package com.alltheducks.javamodeltoclosure.closure;

import com.alltheducks.javamodeltoclosure.closure.ClosureTypeTranslator;
import com.alltheducks.javamodeltoclosure.example.complextypes.ExampleGenericModel;
import com.alltheducks.javamodeltoclosure.example.complextypes.ExampleSimpleModel;
import com.alltheducks.javamodeltoclosure.example.complextypes.ExampleWithComplexTypes;
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
        assertEquals("number", translator.translate(TypeToken.of(Integer.class)));
    }

    @Test
    public void testTranslate_withPrimitiveInt_expectNumber() throws Exception {
        assertEquals("number", translator.translate(TypeToken.of(int.class)));
    }

    @Test
    public void testTranslate_withPrimitiveChar_expectString() throws Exception {
        assertEquals("string", translator.translate(TypeToken.of(char.class)));
    }

    @Test
    public void testTranslate_withPrimitiveBoolean_expectBoolean() throws Exception {
        assertEquals("boolean", translator.translate(TypeToken.of(boolean.class)));
    }

    @Test
    public void testTranslate_withListOfStrings_expectListOfStrings() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("listOfStrings");
        assertEquals("List.<string>", translator.translate(TypeToken.of(field.getGenericType())));
    }

    @Test
    public void testTranslate_withArrayListOfStrings_expectListOfStrings() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("arrayListOfStrings");
        assertEquals("List.<string>", translator.translate(TypeToken.of(field.getGenericType())));
    }

    @Test
    public void testTranslate_withHashMapOfIntegerKeysAndStringValues_expectMapOfNumberKeysAndStringValues() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("hashMapOfIntegerKeysAndStringValues");
        assertEquals("Map.<number,string>", translator.translate(TypeToken.of(field.getGenericType())));
    }

    @Test
    public void testTranslate_withHashMapOfStringKeysAndArrayListOfStringValues_expectMapOfStringKeysAndListOfStringValues() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("hashMapOfStringKeysAndArrayListOfStringValues");
        assertEquals("Map.<string,List.<string>>", translator.translate(TypeToken.of(field.getGenericType())));
    }

    @Test
    public void testTranslate_withListOfStringsArrays_expectMapOfStringKeysAndListOfStringValues() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("listOfStringsArrays");
        assertEquals("Array.<List.<string>>", translator.translate(TypeToken.of(field.getGenericType())));
    }

    @Test
    public void testTranslate_withArrayOfStrings_expectArrayOfStrings() throws Exception {
        assertEquals("Array.<string>", translator.translate(TypeToken.of(String[].class)));
    }

    @Test
    public void testTranslate_withArrayOfIntegers_expectArrayOfNumbers() throws Exception {
        assertEquals("Array.<number>", translator.translate(TypeToken.of(Integer[].class)));
    }

    @Test
    public void testTranslate_withString_expectString() throws Exception {
        assertEquals("string", translator.translate(TypeToken.of(String.class)));
    }

    @Test
    public void testTranslate_withExampleSimpleModel_expectExampleSimpleModel() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("exampleSimpleModel");

        Set<TypeToken<?>> packageTypes = new HashSet<TypeToken<?>>();
        packageTypes.add(TypeToken.of(ExampleSimpleModel.class));
        translator.setPackageTypes(packageTypes);

        assertEquals("ExampleSimpleModel", translator.translate(TypeToken.of(field.getGenericType())));
    }

    @Test
    public void testTranslate_withListOfExampleSimpleModel_expectListOfExampleSimpleModel() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("listOfExampleSimpleModels");

        Set<TypeToken<?>> packageTypes = new HashSet<TypeToken<?>>();
        packageTypes.add(TypeToken.of(ExampleSimpleModel.class));
        translator.setPackageTypes(packageTypes);

        assertEquals("List.<ExampleSimpleModel>", translator.translate(TypeToken.of(field.getGenericType())));
    }

    @Test
    public void testTranslate_withExampleGenericModelWithStringTypeParameters_expectExampleGenericModelWithStringTypeParameters() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("exampleGenericModelWithStringTypeParameters");

        Set<TypeToken<?>> packageTypes = new HashSet<TypeToken<?>>();
        packageTypes.add(TypeToken.of(ExampleGenericModel.class));
        translator.setPackageTypes(packageTypes);

        assertEquals("ExampleGenericModel.<string,string,string>", translator.translate(TypeToken.of(field.getGenericType())));
    }

    @Test
    public void testTranslate_withExampleGenericModelWithGenericTypeParameters_expectExampleGenericModelWithGenericTypeParameters() throws Exception {
        Field field = ExampleWithComplexTypes.class.getDeclaredField("exampleGenericModelWithGenericTypeParameters");

        Set<TypeToken<?>> packageTypes = new HashSet<TypeToken<?>>();
        packageTypes.add(TypeToken.of(ExampleGenericModel.class));
        packageTypes.add(TypeToken.of(ExampleSimpleModel.class));
        translator.setPackageTypes(packageTypes);

        assertEquals("ExampleGenericModel.<ExampleSimpleModel,List.<string>,Map.<number,Array.<number>>>", translator.translate(TypeToken.of(field.getGenericType())));
    }
}
