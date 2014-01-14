package com.alltheducks.remotegenerator.resolver;

import com.alltheducks.remotegenerator.RemoteType;
import com.alltheducks.remotegenerator.exception.FieldTypeResolutionException;
import com.alltheducks.remotegenerator.model.ConvertedType;
import com.alltheducks.remotegenerator.translator.TypeTranslator;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class FieldTypeResolver {

    private TypeTranslator typeTranslator;

    public ConvertedType getFieldType(Field field, Collection<TypeToken<?>> genericParameters) throws FieldTypeResolutionException {
        try {
            RemoteType remoteType = field.getAnnotation(RemoteType.class);
            if(remoteType != null) {
                ConvertedType convertedType = new ConvertedType();
                convertedType.setName(remoteType.value());
                convertedType.setRequires(new HashSet<String>(Arrays.asList(remoteType.requires())));
                return convertedType;
            } else if (genericParameters.contains(TypeToken.of(field.getGenericType()))) {
                ConvertedType convertedType = new ConvertedType();
                convertedType.setName(field.getGenericType().toString());
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
