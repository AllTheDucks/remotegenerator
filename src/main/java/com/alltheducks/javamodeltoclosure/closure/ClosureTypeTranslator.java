package com.alltheducks.javamodeltoclosure.closure;

import com.alltheducks.javamodeltoclosure.exception.TypeDefinitionException;
import com.alltheducks.javamodeltoclosure.exception.TypeTranslationException;
import com.alltheducks.javamodeltoclosure.TypeTranslator;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;

public class ClosureTypeTranslator implements TypeTranslator {

    public static final Map<TypeToken<?>,String> SIMPLE_TRANSLATIONS;
    static {
        SIMPLE_TRANSLATIONS = new HashMap<TypeToken<?>,String>();
        SIMPLE_TRANSLATIONS.put(TypeToken.of(Map.class), "Map");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(List.class), "List");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(String.class), "string");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(Character.class), "string");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(char.class), "string");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(Boolean.class), "boolean");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(boolean.class), "boolean");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(Number.class), "number");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(byte.class), "number");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(short.class), "number");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(int.class), "number");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(long.class), "number");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(float.class), "number");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(double.class), "number");
        SIMPLE_TRANSLATIONS.put(TypeToken.of(Date.class), "goog.date.DateTime");

    }

    private Collection<TypeToken<?>> packageTypes;

    @Override
    public String translate(TypeToken<?> typeToken) throws Exception {
        return translate(typeToken, false);
    }

    private String translate(TypeToken<?> typeToken, boolean skipGenerics) throws TypeTranslationException {

        try {

            if (typeToken.isArray()) {

                return String.format("Array.<%s>",this.translate(typeToken.getComponentType(), skipGenerics));
            }

            if(!skipGenerics) {
                TypeVariable<? extends Class<?>>[] typeParameters = typeToken.getRawType().getTypeParameters();
                if (typeParameters.length > 0) {

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(this.translate(typeToken, true));
                    stringBuilder.append(".<");
                    stringBuilder.append(this.translateGenericType(typeParameters[0], typeToken, skipGenerics));
                    for(int i = 1; i < typeParameters.length; i++) {
                        stringBuilder.append(",");
                        stringBuilder.append(this.translateGenericType(typeParameters[i], typeToken, skipGenerics));
                    }
                    stringBuilder.append(">");

                    return stringBuilder.toString();
                }
            }

            if(packageTypes != null) {
                for(TypeToken<?> packageType : packageTypes) {
                    if(packageType.isAssignableFrom(typeToken)) {
                        //TODO: deal with packages.
                        return packageType.getRawType().getSimpleName();
                    }
                }
            }

            for(TypeToken<?> simpleClass : SIMPLE_TRANSLATIONS.keySet()) {
                if(simpleClass.isAssignableFrom(typeToken)) {
                    return SIMPLE_TRANSLATIONS.get(simpleClass);
                }
            }

        } catch (Exception e) {
            throw new TypeTranslationException("Failed to translate type: " + typeToken.toString(), e);
        }

        throw new TypeDefinitionException("No translation definition for type: " + typeToken.toString());

    }

    private String translateGenericType(Type parameterType, TypeToken<?> typeToken, boolean skipGenerics) throws Exception {
        TypeToken<?> newTypeToken = typeToken.resolveType(parameterType);
        return this.translate(newTypeToken, skipGenerics);
    }

    public Collection<TypeToken<?>> getPackageTypes() {
        return packageTypes;
    }

    public void setPackageTypes(Collection<TypeToken<?>> packageTypes) {
        this.packageTypes = packageTypes;
    }
}
