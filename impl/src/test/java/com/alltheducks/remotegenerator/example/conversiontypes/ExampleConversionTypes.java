package com.alltheducks.remotegenerator.example.conversiontypes;

import com.alltheducks.remotegenerator.RemoteModel;
import com.alltheducks.remotegenerator.RemoteType;

@RemoteModel
public class ExampleConversionTypes {

    private String withoutConversionTypeAnnotation;

    @RemoteType("AnnotationValue")
    private String withConversionTypeAnnotation;

    @RemoteType(value = "AnnotationValue", requires = {"RequiresOne","RequiresTwo"})
    private String withConversionTypeAnnotationAndRequires;

}
