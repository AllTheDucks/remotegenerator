package com.alltheducks.javamodeltoclosure;


import com.alltheducks.javamodeltoclosure.annotation.ConversionModel;
import org.reflections.Reflections;

import java.util.Collection;
import java.util.Set;

public class ConversionModelDiscoveryService {

    public Collection<Class<?>> enumerateClasses(String packageName) {
        Reflections reflections = new Reflections(packageName);
        return reflections.getTypesAnnotatedWith(ConversionModel.class);
    }

}
