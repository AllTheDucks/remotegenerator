package com.alltheducks.remotegenerator.translator;

import com.alltheducks.remotegenerator.exception.TranslationException;
import com.alltheducks.remotegenerator.model.ConvertedType;
import com.alltheducks.remotegenerator.service.RemoteModelDiscoveryService;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;

public abstract class TypeTranslator {

    private RemoteModelDiscoveryService remoteModelDiscoveryService;
    private PackageTranslator packageTranslator;

    public abstract ConvertedType translate(Field field) throws TranslationException;

    public Collection<TypeToken<?>> getPackageTypes() {
        Collection<Class<?>> classes = remoteModelDiscoveryService.enumerateClasses();

        Collection<TypeToken<?>> packageTypes = new HashSet<>();
        for(Class<?> clazz : classes) {
            packageTypes.add(TypeToken.of(clazz));
        }

        return packageTypes;
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
