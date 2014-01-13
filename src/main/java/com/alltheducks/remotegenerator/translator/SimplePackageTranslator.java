package com.alltheducks.remotegenerator.translator;

import java.util.HashMap;

public class SimplePackageTranslator implements PackageTranslator {

    private HashMap<String,String> translations = new HashMap<String,String>();
    private HashMap<String,String> cachedTranslations = new HashMap<String, String>();

    public void addTranslation(String from, String to) {
        translations.put(from, to);
    }

    public void removeTranslation(String from) {
        translations.remove(from);
    }


    @Override
    public String translate(String className) {
        if(cachedTranslations.containsKey(className)) {
            return cachedTranslations.get(className);
        }

        String newClassName;

        int lastDot = className.lastIndexOf(".");
        if(lastDot <= 0) {
            newClassName = className;
        } else {
            String packageName = className.substring(0, lastDot);
            if(translations.containsKey(packageName)) {
                newClassName = translations.get(packageName) + className.substring(lastDot);
            } else {
                newClassName = className;
            }
        }

        cachedTranslations.put(className, newClassName);

        return newClassName;
    }

}
