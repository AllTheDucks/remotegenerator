package com.alltheducks.javamodeltoclosure;

import com.alltheducks.javamodeltoclosure.closure.ClosureRenderer;
import com.alltheducks.javamodeltoclosure.closure.ClosureTypeTranslator;
import com.alltheducks.javamodeltoclosure.model.ConvertedModel;
import com.alltheducks.javamodeltoclosure.renderer.ModelRenderer;
import com.alltheducks.javamodeltoclosure.resolver.FieldTypeResolver;
import com.alltheducks.javamodeltoclosure.service.ConversionModelDiscoveryService;
import com.alltheducks.javamodeltoclosure.service.ConvertedFieldService;
import com.alltheducks.javamodeltoclosure.service.ConvertedModelService;
import com.alltheducks.javamodeltoclosure.service.FieldDiscoveryService;
import com.alltheducks.javamodeltoclosure.translator.SimplePackageTranslator;
import com.alltheducks.javamodeltoclosure.translator.TypeTranslator;

import java.util.Collection;
import java.util.Iterator;

public class JavaModelToClosure {

    public static void main(String[] args) {
        try {
            String packageName = "com.alltheducks.javamodeltoclosure.example";

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
