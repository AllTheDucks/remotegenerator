package com.alltheducks.javamodeltoclosure;

import com.alltheducks.javamodeltoclosure.closure.ClosureRenderer;
import com.alltheducks.javamodeltoclosure.closure.ClosureTypeTranslator;
import com.alltheducks.javamodeltoclosure.model.ConvertedModel;
import com.alltheducks.javamodeltoclosure.renderer.ModelRenderer;
import com.alltheducks.javamodeltoclosure.resolver.FieldTypeResolver;
import com.alltheducks.javamodeltoclosure.translator.TypeTranslator;

import java.util.Collection;
import java.util.Iterator;

public class JavaModelToClosure {

    public static void main(String[] args) {
        try {
            ConversionModelDiscoveryService conversionModelDiscoveryService = new ConversionModelDiscoveryService();
            Collection<Class<?>> conversionModels = conversionModelDiscoveryService.enumerateClasses("com.alltheducks.javamodeltoclosure.example");

            TypeTranslator typeTranslator = new ClosureTypeTranslator();
            typeTranslator.addPackageClasses(conversionModels);

            FieldTypeResolver fieldTypeResolver = new FieldTypeResolver();
            fieldTypeResolver.setTypeTranslator(typeTranslator);

            FieldDiscoveryService fieldDiscoveryService = new FieldDiscoveryService();

            ConvertedFieldService convertedFieldService = new ConvertedFieldService();
            convertedFieldService.setFieldDiscoveryService(fieldDiscoveryService);
            convertedFieldService.setFieldTypeResolver(fieldTypeResolver);

            ConvertedModelService convertedModelService = new ConvertedModelService();
            convertedModelService.setConvertedFieldService(convertedFieldService);

            Collection<ConvertedModel> convertedModels = convertedModelService.getAllConvertedModels(conversionModels);

            ModelRenderer renderer = new ClosureRenderer();

            Iterator<ConvertedModel> iterator = convertedModels.iterator();
            while(iterator.hasNext()) {
                renderer.render(iterator.next(), System.out);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
