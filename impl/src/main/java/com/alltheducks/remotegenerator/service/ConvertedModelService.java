package com.alltheducks.remotegenerator.service;

import com.alltheducks.remotegenerator.exception.FieldTypeResolutionException;
import com.alltheducks.remotegenerator.model.ConvertedField;
import com.alltheducks.remotegenerator.model.ConvertedModel;
import com.alltheducks.remotegenerator.translator.PackageTranslator;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.TypeVariable;
import java.util.*;

public class ConvertedModelService {

    private ConvertedFieldService convertedFieldService;
    private PackageTranslator packageTranslator;
    private RemoteModelDiscoveryService remoteModelDiscoveryService;

    public ConvertedModel getConvertedModel(Class<?> clazz) throws FieldTypeResolutionException {
        ConvertedModel convertedModel = new ConvertedModel();
        convertedModel.setName(this.getPackageTranslator().translate(clazz.getName()));

        TypeVariable<? extends Class<?>>[] typeParameters = clazz.getTypeParameters();
        Set<TypeToken<?>> genericParameters = new HashSet<>(typeParameters.length);
        List<String> genericParameterNames = new ArrayList<>(typeParameters.length);
        for(TypeVariable<? extends Class<?>> typeVariable : typeParameters) {
            TypeToken<?> typeToken = TypeToken.of(typeVariable);
            genericParameters.add(typeToken);
            genericParameterNames.add(typeToken.toString());
        }
        convertedModel.setGenericParameters(genericParameterNames);

        Set<ConvertedField> convertedFields = convertedFieldService.getAllConvertedFields(clazz, genericParameters);
        convertedModel.setConvertedFields(convertedFields);

        SortedSet<String> requires = new TreeSet<>();
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

    public Collection<ConvertedModel> enumerateModels() throws FieldTypeResolutionException {
        Collection<Class<?>> classes = remoteModelDiscoveryService.enumerateClasses();
        return this.getAllConvertedModels(classes);
    }

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

    public RemoteModelDiscoveryService getRemoteModelDiscoveryService() {
        return remoteModelDiscoveryService;
    }

    public void setRemoteModelDiscoveryService(RemoteModelDiscoveryService remoteModelDiscoveryService) {
        this.remoteModelDiscoveryService = remoteModelDiscoveryService;
    }
}
