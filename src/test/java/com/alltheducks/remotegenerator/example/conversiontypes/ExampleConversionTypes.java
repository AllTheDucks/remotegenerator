package com.alltheducks.remotegenerator.example.conversiontypes;

import com.alltheducks.remotegenerator.annotation.ConversionModel;
import com.alltheducks.remotegenerator.annotation.ConversionType;

@ConversionModel
public class ExampleConversionTypes {

    private String withoutConversionTypeAnnotation;

    @ConversionType("AnnotationValue")
    private String withConversionTypeAnnotation;

    @ConversionType(value = "AnnotationValue", requires = {"RequiresOne","RequiresTwo"})
    private String withConversionTypeAnnotationAndRequires;

}
