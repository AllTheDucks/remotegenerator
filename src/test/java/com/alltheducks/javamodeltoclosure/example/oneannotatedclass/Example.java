package com.alltheducks.javamodeltoclosure.example.oneannotatedclass;

import com.alltheducks.javamodeltoclosure.annotation.ConversionModel;

@ConversionModel
public class Example {

    private String aString;
    private int anInt;

    public String getaString() {
        return aString;
    }

    public void setaString(String aString) {
        this.aString = aString;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }
}
