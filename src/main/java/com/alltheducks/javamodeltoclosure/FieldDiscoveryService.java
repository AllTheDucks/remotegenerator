package com.alltheducks.javamodeltoclosure;

import com.alltheducks.javamodeltoclosure.annotation.ConversionIgnore;
import com.google.common.base.Predicates;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Set;

public class FieldDiscoveryService {

    private Class<?> clazz;

    public FieldDiscoveryService(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Set<Field> enumerateFields() {
        return ReflectionUtils.getAllFields(clazz, Predicates.not(ReflectionUtils.withAnnotation(ConversionIgnore.class)));
    }

}
