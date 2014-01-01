package com.alltheducks.javamodeltoclosure.model;

import java.util.List;
import java.util.Set;

public class ConvertedModel {

    private String name;
    private Set<ConvertedField> convertedFields;
    private Set<String> requires;

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

    public Set<String> getRequires() {
        return requires;
    }

    public void setRequires(Set<String> requires) {
        this.requires = requires;
    }
}
