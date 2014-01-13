package com.alltheducks.remotegenerator.example.types;

import com.alltheducks.remotegenerator.annotation.ConversionModel;

import java.util.*;

@ConversionModel
public class ExampleWithComplexTypes {

    private List<String> listOfStrings;
    private ArrayList<String> arrayListOfStrings;
    private HashMap<Integer, String> hashMapOfIntegerKeysAndStringValues;
    private HashMap<String, ArrayList<String>> hashMapOfStringKeysAndArrayListOfStringValues;
    private List<String>[] listOfStringsArrays;
    private ExampleSimpleModel exampleSimpleModel;
    private List<ExampleSimpleModel> listOfExampleSimpleModels;
    private ExampleGenericModel<String,String,String> exampleGenericModelWithStringTypeParameters;
    private ExampleGenericModel<ExampleSimpleModel,List<Date>,Map<Integer,Double[]>> exampleGenericModelWithGenericTypeParameters;

}
