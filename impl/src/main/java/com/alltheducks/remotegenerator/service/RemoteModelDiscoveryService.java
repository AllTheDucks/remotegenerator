package com.alltheducks.remotegenerator.service;


import com.alltheducks.remotegenerator.RemoteModel;
import org.reflections.Reflections;

import java.util.Collection;

public class RemoteModelDiscoveryService {

    public Collection<Class<?>> enumerateClasses(String packageName) {
        Reflections reflections = new Reflections(packageName);
        return reflections.getTypesAnnotatedWith(RemoteModel.class);
    }

}
