package com.alltheducks.javamodeltoclosure.example.complextypes;

import com.alltheducks.javamodeltoclosure.annotation.ConversionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ExampleGenericModel<ExampleSimpleModel,List<String>,Map<Integer,Double[]>> exampleGenericModelWithGenericTypeParameters;

}
