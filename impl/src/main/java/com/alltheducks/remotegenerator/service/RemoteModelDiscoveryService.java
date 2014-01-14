package com.alltheducks.remotegenerator.service;


import com.alltheducks.remotegenerator.RemoteModel;
import org.reflections.Reflections;

import java.util.*;

public class RemoteModelDiscoveryService {

    private Set<String> packages = new HashSet<String>();

    private Map<String, Collection<Class<?>>> classCache = new HashMap<>();
    private Collection<Class<?>> allClassesCache;

    public Collection<Class<?>> enumerateClasses() {
        if(allClassesCache != null) {
            return allClassesCache;
        }

        Collection<Class<?>> classes = new HashSet<>();
        for(String packageName : packages) {
            classes.addAll(enumerateClassesForPackage(packageName));
        }

        allClassesCache = classes;

        return classes;
    }

    protected Collection<Class<?>> enumerateClassesForPackage(String packageName) {
        if(classCache.containsKey(packageName)) {
            return classCache.get(packageName);
        }

        Reflections reflections = new Reflections(packageName);
        Collection<Class<?>> classes = reflections.getTypesAnnotatedWith(RemoteModel.class);

        classCache.put(packageName, classes);

        return classes;
    }

    public void addPackage(String packageName) {
        packages.add(packageName);
        allClassesCache = null;
    }

    public void removePackage(String packageName) {
        packages.remove(packageName);
        classCache.remove(packageName);
        allClassesCache = null;
    }


}
