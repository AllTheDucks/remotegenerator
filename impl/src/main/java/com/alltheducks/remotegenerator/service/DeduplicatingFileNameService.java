package com.alltheducks.remotegenerator.service;

import java.util.HashSet;
import java.util.Set;

public class DeduplicatingFileNameService extends FileNameService {

    private FileNameService fileNameService;
    private Set<String> previousTranslations = new HashSet<String>();

    @Override
    public String getFileNameForClassWithoutExtension(String className) {
        String baseTranslation = fileNameService.getFileNameForClassWithoutExtension(className);

        int count = 1;
        String translation = baseTranslation;
        while(previousTranslations.contains(translation)) {
            translation = String.format("%s(%s)", baseTranslation, count++);
        }

        previousTranslations.add(translation);

        return translation;
    }

    public FileNameService getFileNameService() {
        return fileNameService;
    }

    public void setFileNameService(FileNameService fileNameService) {
        this.fileNameService = fileNameService;
    }
}
