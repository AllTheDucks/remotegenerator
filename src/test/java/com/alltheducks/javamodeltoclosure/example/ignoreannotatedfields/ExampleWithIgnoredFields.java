package com.alltheducks.javamodeltoclosure.example.ignoreannotatedfields;

import com.alltheducks.javamodeltoclosure.annotation.ConversionIgnore;

public class ExampleWithIgnoredFields {

    @ConversionIgnore
    private String ignoredString;

    private String aString;

}
