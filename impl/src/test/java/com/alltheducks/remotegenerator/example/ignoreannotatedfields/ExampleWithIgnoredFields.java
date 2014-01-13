package com.alltheducks.remotegenerator.example.ignoreannotatedfields;

import com.alltheducks.remotegenerator.RemoteIgnore;

public class ExampleWithIgnoredFields {

    @RemoteIgnore
    private String ignoredString;

    private String aString;

}
