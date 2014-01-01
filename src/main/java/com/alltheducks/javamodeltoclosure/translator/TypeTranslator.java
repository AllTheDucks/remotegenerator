package com.alltheducks.javamodeltoclosure.translator;

import com.alltheducks.javamodeltoclosure.exception.TranslationException;
import com.alltheducks.javamodeltoclosure.model.ConvertedType;

import java.lang.reflect.Field;
import java.util.Collection;

public interface TypeTranslator {

    ConvertedType translate(Field field) throws TranslationException;

    void addPackageClasses(Collection<Class<?>> classes);

}
