package com.alltheducks.remotegenerator.example.types;

import com.alltheducks.remotegenerator.annotation.ConversionModel;

@ConversionModel
public class ExampleGenericModel<T,U,V> {

    private T fieldT;
    private U fieldU;
    private V fieldV;

}
