package com.alltheducks.remotegenerator.service;

public abstract class FileNameService {

    private String extension;

    public abstract String getFileNameForClassWithoutExtension(String className);

    public String getFileNameForClass(String className) {
        if(extension == null || extension.isEmpty()) {
            return getFileNameForClassWithoutExtension(className);
        } else {
            return String.format("%s.%s", getFileNameForClassWithoutExtension(className), extension);
        }
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
