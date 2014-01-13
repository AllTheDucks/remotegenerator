package com.alltheducks.remotegenerator.translator;

import com.alltheducks.remotegenerator.exception.TranslationException;
import com.alltheducks.remotegenerator.model.ConvertedType;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;

public abstract class TypeTranslator {

    private Collection<TypeToken<?>> packageTypes = new HashSet<TypeToken<?>>();
    private PackageTranslator packageTranslator;

    public abstract ConvertedType translate(Field field) throws TranslationException;

    public void addPackageClasses(Collection<Class<?>> classes) {
        for(Class<?> clazz : classes) {
            packageTypes.add(TypeToken.of(clazz));
        }
    }

    public Collection<TypeToken<?>> getPackageTypes() {
        return packageTypes;
    }

    public void setPackageTypes(Collection<TypeToken<?>> packageTypes) {
        this.packageTypes = packageTypes;
    }

    public PackageTranslator getPackageTranslator() {
        return packageTranslator;
    }

    public void setPackageTranslator(PackageTranslator packageTranslator) {
        this.packageTranslator = packageTranslator;
    }
}
