package com.alltheducks.javamodeltoclosure.example.complextypes;

import com.alltheducks.javamodeltoclosure.annotation.ConversionModel;

@ConversionModel
public class ExampleGenericModel<T,U,V> {

    private T fieldT;
    private U fieldU;
    private V fieldV;

}
