package com.alltheducks.remotegenerator.service;


import com.alltheducks.remotegenerator.ConversionModel;
import org.reflections.Reflections;

import java.util.Collection;

public class ConversionModelDiscoveryService {

    public Collection<Class<?>> enumerateClasses(String packageName) {
        Reflections reflections = new Reflections(packageName);
        return reflections.getTypesAnnotatedWith(ConversionModel.class);
    }

}
