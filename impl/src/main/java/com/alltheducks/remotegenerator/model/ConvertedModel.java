package com.alltheducks.remotegenerator.model;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public class ConvertedModel {

    private String name;
    private Set<ConvertedField> convertedFields;
    private SortedSet<String> requires;
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

    public SortedSet<String> getRequires() {
        return requires;
    }

    public void setRequires(SortedSet<String> requires) {
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
