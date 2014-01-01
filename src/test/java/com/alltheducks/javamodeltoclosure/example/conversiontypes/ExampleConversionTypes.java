package com.alltheducks.javamodeltoclosure.example.conversiontypes;

import com.alltheducks.javamodeltoclosure.annotation.ConversionModel;
import com.alltheducks.javamodeltoclosure.annotation.ConversionType;

@ConversionModel
public class ExampleConversionTypes {

    private String withoutConversionTypeAnnotation;

    @ConversionType("AnnotationValue")
    private String withConversionTypeAnnotation;

    @ConversionType(value = "AnnotationValue", requires = {"RequiresOne","RequiresTwo"})
    private String withConversionTypeAnnotationAndRequires;

}
