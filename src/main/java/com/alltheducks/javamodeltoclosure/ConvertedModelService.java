package com.alltheducks.javamodeltoclosure;

import com.alltheducks.javamodeltoclosure.exception.FieldTypeResolutionException;
import com.alltheducks.javamodeltoclosure.model.ConvertedField;
import com.alltheducks.javamodeltoclosure.model.ConvertedModel;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConvertedModelService {

    private ConvertedFieldService convertedFieldService;

    public ConvertedModel getConvertedModel(Class<?> clazz) throws FieldTypeResolutionException {
        ConvertedModel convertedModel = new ConvertedModel();
        convertedModel.setName(clazz.getSimpleName());

        Set<ConvertedField> convertedFields = convertedFieldService.getAllConvertedFields(clazz);
        convertedModel.setConvertedFields(convertedFields);

        Set<String> requires = new HashSet<String>();
        for(ConvertedField convertedField : convertedFields) {
            if(convertedField.getType().getRequires() != null) {
                requires.addAll(convertedField.getType().getRequires());
            }
        }
        convertedModel.setRequires(requires);

        return convertedModel;
    }

    public Collection<ConvertedModel> getAllConvertedModels(Collection<Class<?>> classes) throws FieldTypeResolutionException {
        Set<ConvertedModel> convertedModels = new HashSet<ConvertedModel>(classes.size());
        for(Class<?> clazz : classes) {
            convertedModels.add(this.getConvertedModel(clazz));
        }
        return convertedModels;
    }

//    public Set<ConvertedModel> getAllConvertedModels(String packageName) throws FieldTypeResolutionException {
//        Set<Class<?>> classes = conversionModelDiscoveryService.enumerateClasses(packageName);
//        return this.getAllConvertedModels(classes);
//    }

    public ConvertedFieldService getConvertedFieldService() {
        return convertedFieldService;
    }

    public void setConvertedFieldService(ConvertedFieldService convertedFieldService) {
        this.convertedFieldService = convertedFieldService;
    }

}
