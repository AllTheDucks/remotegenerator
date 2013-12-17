package com.alltheducks.javamodeltoclosure;

import com.alltheducks.javamodeltoclosure.annotation.ConversionType;
import com.alltheducks.javamodeltoclosure.exception.FieldTypeResolutionException;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Field;

public class FieldTypeResolver {

    private TypeTranslator typeTranslator;

    public String getFieldType(Field field) throws FieldTypeResolutionException {
        try {
            ConversionType conversionType = field.getAnnotation(ConversionType.class);
            if(conversionType != null) {
                return conversionType.value();
            } else {
                return typeTranslator.translate(TypeToken.of(field.getGenericType()));
            }
        } catch (Exception e) {
            throw new FieldTypeResolutionException("Failed to resolve field type.", e);
        }
    }

    public TypeTranslator getTypeTranslator() {
        return typeTranslator;
    }

    public void setTypeTranslator(TypeTranslator typeTranslator) {
        this.typeTranslator = typeTranslator;
    }
}
