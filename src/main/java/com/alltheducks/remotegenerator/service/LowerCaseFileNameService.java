package com.alltheducks.remotegenerator.service;

public class LowerCaseFileNameService extends FileNameService {

    @Override
    public String getFileNameForClassWithoutExtension(String className) {
        return className.substring(className.lastIndexOf('.') + 1).toLowerCase();
    }

}
