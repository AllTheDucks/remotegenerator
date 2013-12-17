package com.alltheducks.javamodeltoclosure;

import com.alltheducks.javamodeltoclosure.exception.FieldTypeResolutionException;
import com.alltheducks.javamodeltoclosure.model.ConvertedField;
import com.alltheducks.javamodeltoclosure.resolver.FieldTypeResolver;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ConversionFieldService {

    FieldTypeResolver fieldTypeResolver;
    FieldDiscoveryService fieldDiscoveryService;

    public ConvertedField getConversionField(Field field) throws FieldTypeResolutionException {
        ConvertedField convertedField = new ConvertedField();

        convertedField.setName(field.getName());
        convertedField.setType(fieldTypeResolver.getFieldType(field));

        return convertedField;
    }

    public Set<ConvertedField> getAllConversionFields(Set<Field> fields) throws FieldTypeResolutionException {
        HashSet<ConvertedField> convertedFields = new HashSet<ConvertedField>();
        for(Field field : fields) {
            convertedFields.add(this.getConversionField(field));
        }
        return convertedFields;
    }

    public Set<ConvertedField> getAllConversionFields(Class<?> clazz) throws FieldTypeResolutionException {
        Set<Field> fields = fieldDiscoveryService.enumerateFields(clazz);
        return this.getAllConversionFields(fields);
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
