package com.alltheducks.javamodeltoclosure.translator;

import com.alltheducks.javamodeltoclosure.exception.TranslationException;
import com.alltheducks.javamodeltoclosure.model.ConvertedType;

import java.lang.reflect.Field;

public interface TypeTranslator {

    ConvertedType translate(Field field) throws TranslationException;

}
