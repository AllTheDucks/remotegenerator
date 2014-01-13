package com.alltheducks.remotegenerator.example.ignoreannotatedfields;

import com.alltheducks.remotegenerator.ConversionIgnore;

public class ExampleWithIgnoredFields {

    @ConversionIgnore
    private String ignoredString;

    private String aString;

}
