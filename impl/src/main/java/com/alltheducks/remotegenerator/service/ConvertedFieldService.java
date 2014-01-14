package com.alltheducks.remotegenerator.service;

import com.alltheducks.remotegenerator.exception.FieldTypeResolutionException;
import com.alltheducks.remotegenerator.model.ConvertedField;
import com.alltheducks.remotegenerator.resolver.FieldTypeResolver;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Field;
import java.util.*;

public class ConvertedFieldService {

    FieldTypeResolver fieldTypeResolver;
    FieldDiscoveryService fieldDiscoveryService;

    public ConvertedField getConvertedField(Field field, Collection<TypeToken<?>> genericParameters) throws FieldTypeResolutionException {
        ConvertedField convertedField = new ConvertedField();

        convertedField.setName(field.getName());
        convertedField.setType(fieldTypeResolver.getFieldType(field, genericParameters));

        return convertedField;
    }

    public Set<ConvertedField> getAllConvertedFields(Collection<Field> fields, Collection<TypeToken<?>> genericParameters) throws FieldTypeResolutionException {
        Set<ConvertedField> convertedFields = new HashSet<ConvertedField>(fields.size());
        for(Field field : fields) {
            convertedFields.add(this.getConvertedField(field, genericParameters));
        }
        return convertedFields;
    }

    public Set<ConvertedField> getAllConvertedFields(Class<?> clazz, Collection<TypeToken<?>> genericParameters) throws FieldTypeResolutionException {
        Collection<Field> fields = fieldDiscoveryService.enumerateFields(clazz);
        return this.getAllConvertedFields(fields, genericParameters);
    }

    public FieldTypeResolver getFieldTypeResolver() {
        return fieldTypeResolver;
    }

    public void setFieldTypeResolver(FieldTypeResolver fieldTypeResolver) {
        this.fieldTypeResolver = fieldTypeResolver;
    }

    public FieldDiscoveryService getFieldDiscoveryService() {
        return fieldDiscoveryService;
    }

    public void setFieldDiscoveryService(FieldDiscoveryService fieldDiscoveryService) {
        this.fieldDiscoveryService = fieldDiscoveryService;
    }
}
