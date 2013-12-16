package com.alltheducks.javamodeltoclosure;


import org.reflections.Reflections;

import java.util.Set;

public class ClosureModelDiscoveryService {

    Reflections reflections;

    public ClosureModelDiscoveryService(String packageName) {
        reflections = new Reflections(packageName);
    }

    public Set<Class<?>> enumerateClasses() {
        return reflections.getTypesAnnotatedWith(ClosureModel.class);
    }

}
