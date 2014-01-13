package com.alltheducks.remotegenerator.example.ignoreannotatedfields;

import com.alltheducks.remotegenerator.RemoteIgnore;
import com.alltheducks.remotegenerator.RemoteModel;

@RemoteModel
public class ExampleWithIgnoredFields {

    @RemoteIgnore
    private String ignoredString;

    private String aString;

}
