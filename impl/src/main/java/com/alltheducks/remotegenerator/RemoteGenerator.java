package com.alltheducks.remotegenerator;

import com.alltheducks.remotegenerator.closure.ClosureRenderer;
import com.alltheducks.remotegenerator.closure.ClosureTypeTranslator;
import com.alltheducks.remotegenerator.model.ConvertedModel;
import com.alltheducks.remotegenerator.renderer.ModelRenderer;
import com.alltheducks.remotegenerator.resolver.FieldTypeResolver;
import com.alltheducks.remotegenerator.service.*;
import com.alltheducks.remotegenerator.translator.SimplePackageTranslator;
import com.alltheducks.remotegenerator.translator.TypeTranslator;

import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Iterator;

public class RemoteGenerator {

    public static void main(String[] args) {
        try {
            String packageName = "com.alltheducks.remotegenerator.example.types";

            RemoteModelDiscoveryService remoteModelDiscoveryService = new RemoteModelDiscoveryService();
            Collection<Class<?>> conversionModels = remoteModelDiscoveryService.enumerateClasses(packageName);

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

            LowerCaseFileNameService lowerCaseFileNameService = new LowerCaseFileNameService();

            DeduplicatingFileNameService deduplicatingFileNameService = new DeduplicatingFileNameService();
            deduplicatingFileNameService.setFileNameService(lowerCaseFileNameService);
            deduplicatingFileNameService.setExtension("js");

            FileOutputStreamService fileOutputStreamService = new FileOutputStreamService();
            fileOutputStreamService.setFileNameService(deduplicatingFileNameService);
            fileOutputStreamService.setBasePath("/tmp/remotegeneratortest/");

            Iterator<ConvertedModel> iterator = convertedModels.iterator();
            while(iterator.hasNext()) {
                ConvertedModel convertedModel = iterator.next();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStreamService.getOutputStreamForClass(convertedModel.getName()));
                renderer.render(convertedModel, outputStreamWriter);
                outputStreamWriter.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
