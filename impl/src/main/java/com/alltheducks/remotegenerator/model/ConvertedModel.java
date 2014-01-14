package com.alltheducks.remotegenerator.model;

import java.util.List;
import java.util.Set;

public class ConvertedModel {

    private String name;
    private Set<ConvertedField> convertedFields;
    private Set<String> requires;
    private List<String> genericParameters;
    private String originalName;

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

    public List<String> getGenericParameters() {
        return genericParameters;
    }

    public void setGenericParameters(List<String> genericParameters) {
        this.genericParameters = genericParameters;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
}
