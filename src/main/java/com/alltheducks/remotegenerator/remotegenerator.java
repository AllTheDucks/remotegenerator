package com.alltheducks.remotegenerator;

import com.alltheducks.remotegenerator.closure.ClosureRenderer;
import com.alltheducks.remotegenerator.closure.ClosureTypeTranslator;
import com.alltheducks.remotegenerator.model.ConvertedModel;
import com.alltheducks.remotegenerator.renderer.ModelRenderer;
import com.alltheducks.remotegenerator.resolver.FieldTypeResolver;
import com.alltheducks.remotegenerator.service.ConversionModelDiscoveryService;
import com.alltheducks.remotegenerator.service.ConvertedFieldService;
import com.alltheducks.remotegenerator.service.ConvertedModelService;
import com.alltheducks.remotegenerator.service.FieldDiscoveryService;
import com.alltheducks.remotegenerator.translator.SimplePackageTranslator;
import com.alltheducks.remotegenerator.translator.TypeTranslator;

import java.util.Collection;
import java.util.Iterator;

public class remotegenerator {

    public static void main(String[] args) {
        try {
            String packageName = "com.alltheducks.remotegenerator.example";

            ConversionModelDiscoveryService conversionModelDiscoveryService = new ConversionModelDiscoveryService();
            Collection<Class<?>> conversionModels = conversionModelDiscoveryService.enumerateClasses(packageName);

            SimplePackageTranslator simplePackageTranslator = new SimplePackageTranslator();
            simplePackageTranslator.addTranslation(packageName, "jsh");

            TypeTranslator typeTranslator = new ClosureTypeTranslator();
            typeTranslator.addPackageClasses(conversionModels);
            typeTranslator.setPackageTranslator(simplePackageTranslator);

            FieldTypeResolver fieldTypeResolver = new FieldTypeResolver();
            fieldTypeResolver.setTypeTranslator(typeTranslator);

            FieldDiscoveryService fieldDiscoveryService = new FieldDiscoveryService();

            ConvertedFieldService convertedFieldService = new ConvertedFieldService();
            convertedFieldService.setFieldDiscoveryService(fieldDiscoveryService);
            convertedFieldService.setFieldTypeResolver(fieldTypeResolver);

            ConvertedModelService convertedModelService = new ConvertedModelService();
            convertedModelService.setConvertedFieldService(convertedFieldService);
            convertedModelService.setPackageTranslator(simplePackageTranslator);

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
