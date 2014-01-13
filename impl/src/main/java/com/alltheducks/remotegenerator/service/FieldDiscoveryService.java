package com.alltheducks.remotegenerator.service;

import com.alltheducks.remotegenerator.RemoteIgnore;
import com.google.common.base.Predicates;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;

public class FieldDiscoveryService {

    public Collection<Field> enumerateFields(Class<?> clazz) {
        return ReflectionUtils.getAllFields(clazz, Predicates.not(ReflectionUtils.withAnnotation(RemoteIgnore.class)));
    }

}
