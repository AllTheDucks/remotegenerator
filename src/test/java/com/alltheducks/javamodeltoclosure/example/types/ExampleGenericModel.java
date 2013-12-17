package com.alltheducks.javamodeltoclosure.example.types;

import com.alltheducks.javamodeltoclosure.annotation.ConversionModel;

@ConversionModel
public class ExampleGenericModel<T,U,V> {

    private T fieldT;
    private U fieldU;
    private V fieldV;

}
