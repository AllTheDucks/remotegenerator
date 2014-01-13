package com.alltheducks.remotegenerator.service;

import com.alltheducks.remotegenerator.exception.FieldTypeResolutionException;
import com.alltheducks.remotegenerator.model.ConvertedField;
import com.alltheducks.remotegenerator.model.ConvertedModel;
import com.alltheducks.remotegenerator.translator.PackageTranslator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ConvertedModelService {

    private ConvertedFieldService convertedFieldService;
    private PackageTranslator packageTranslator;

    public ConvertedModel getConvertedModel(Class<?> clazz) throws FieldTypeResolutionException {
        ConvertedModel convertedModel = new ConvertedModel();
        convertedModel.setName(this.getPackageTranslator().translate(clazz.getName()));

        Set<ConvertedField> convertedFields = convertedFieldService.getAllConvertedFields(clazz);
        convertedModel.setConvertedFields(convertedFields);

        Set<String> requires = new HashSet<String>();
        for(ConvertedField convertedField : convertedFields) {
            if(convertedField.getType().getRequires() != null) {
                requires.addAll(convertedField.getType().getRequires());
            }
        }
        convertedModel.setRequires(requires);

        convertedModel.setOriginalName(clazz.getName());

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

    public PackageTranslator getPackageTranslator() {
        return packageTranslator;
    }

    public void setPackageTranslator(PackageTranslator packageTranslator) {
        this.packageTranslator = packageTranslator;
    }
}
