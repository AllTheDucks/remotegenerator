package com.alltheducks.javamodeltoclosure;

import com.alltheducks.javamodeltoclosure.annotation.ConversionIgnore;
import com.google.common.base.Predicates;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

public class FieldDiscoveryService {

    public Collection<Field> enumerateFields(Class<?> clazz) {
        return ReflectionUtils.getAllFields(clazz, Predicates.not(ReflectionUtils.withAnnotation(ConversionIgnore.class)));
    }

}
