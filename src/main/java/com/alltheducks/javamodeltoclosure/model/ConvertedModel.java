package com.alltheducks.javamodeltoclosure.model;

import java.util.Set;

public class ConvertedModel {

    private String name;
    private Set<ConvertedField> convertedFields;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ConvertedField> getConvertedFields() {
        return convertedFields;
    }

    public void setConvertedFields(Set<ConvertedField> convertedFields) {
        this.convertedFields = convertedFields;
    }
}
