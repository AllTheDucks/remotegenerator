package com.alltheducks.javamodeltoclosure.resolver;

import com.alltheducks.javamodeltoclosure.annotation.ConversionType;
import com.alltheducks.javamodeltoclosure.exception.FieldTypeResolutionException;
import com.alltheducks.javamodeltoclosure.model.ConvertedType;
import com.alltheducks.javamodeltoclosure.translator.TypeTranslator;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;

public class FieldTypeResolver {

    private TypeTranslator typeTranslator;

    public ConvertedType getFieldType(Field field) throws FieldTypeResolutionException {
        try {
            ConversionType conversionType = field.getAnnotation(ConversionType.class);
            if(conversionType != null) {
                ConvertedType convertedType = new ConvertedType();
                convertedType.setName(conversionType.value());
                convertedType.setRequires(new HashSet<String>(Arrays.asList(conversionType.requires())));
                return convertedType;
            } else {
                return typeTranslator.translate(field);
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
