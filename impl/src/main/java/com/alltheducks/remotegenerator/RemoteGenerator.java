package com.alltheducks.remotegenerator;

import com.alltheducks.remotegenerator.closure.ClosureTypeTranslator;
import com.alltheducks.remotegenerator.model.ConvertedModel;
import com.alltheducks.remotegenerator.resolver.FieldTypeResolver;
import com.alltheducks.remotegenerator.service.*;
import com.alltheducks.remotegenerator.soy.SoyModelRenderer;
import com.alltheducks.remotegenerator.translator.SimplePackageTranslator;
import com.alltheducks.remotegenerator.translator.TypeTranslator;

import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Iterator;

public class RemoteGenerator {

    public static void main(String[] args) {
        try {
            String packageName = "com.alltheducks.remotegenerator.example.types";
            String newPackageName = "jsh";
            String outputPath = "/tmp/remotegeneratortest/";

            RemoteModelDiscoveryService remoteModelDiscoveryService = new RemoteModelDiscoveryService();
            Collection<Class<?>> remoteModels = remoteModelDiscoveryService.enumerateClasses(packageName);

            SimplePackageTranslator simplePackageTranslator = new SimplePackageTranslator();
            simplePackageTranslator.addTranslation(packageName, newPackageName);

            TypeTranslator typeTranslator = new ClosureTypeTranslator();
            typeTranslator.addPackageClasses(remoteModels);
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

            Collection<ConvertedModel> convertedModels = convertedModelService.getAllConvertedModels(remoteModels);

            SoyModelRenderer soyModelRenderer = new SoyModelRenderer();
            soyModelRenderer.setSoyFilePath("templates/closure.soy");

            LowerCaseFileNameService lowerCaseFileNameService = new LowerCaseFileNameService();

            DeduplicatingFileNameService deduplicatingFileNameService = new DeduplicatingFileNameService();
            deduplicatingFileNameService.setFileNameService(lowerCaseFileNameService);
            deduplicatingFileNameService.setExtension("js");

            FileOutputStreamService fileOutputStreamService = new FileOutputStreamService();
            fileOutputStreamService.setFileNameService(deduplicatingFileNameService);
            fileOutputStreamService.setBasePath(outputPath);

            for (ConvertedModel convertedModel : convertedModels) {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStreamService.getOutputStreamForClass(convertedModel.getName()));
                soyModelRenderer.render(convertedModel, outputStreamWriter);
                outputStreamWriter.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
