package com.alltheducks.remotegenerator.model;

import java.util.Set;

public class ConvertedType {

    private String name;
    private Set<String> requires;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getRequires() {
        return requires;
    }

    public void setRequires(Set<String> requires) {
        this.requires = requires;
    }

}
