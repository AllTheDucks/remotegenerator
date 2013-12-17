package com.alltheducks.javamodeltoclosure;


import com.alltheducks.javamodeltoclosure.annotation.ConversionModel;
import org.reflections.Reflections;

import java.util.Set;

public class ConversionModelDiscoveryService {

    Reflections reflections;

    public ConversionModelDiscoveryService(String packageName) {
        reflections = new Reflections(packageName);
    }

    public Set<Class<?>> enumerateClasses() {
        return reflections.getTypesAnnotatedWith(ConversionModel.class);
    }

}
