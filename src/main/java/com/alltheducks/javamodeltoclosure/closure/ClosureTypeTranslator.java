package com.alltheducks.javamodeltoclosure.closure;

import com.alltheducks.javamodeltoclosure.exception.TranslationException;
import com.alltheducks.javamodeltoclosure.exception.TypeDefinitionException;
import com.alltheducks.javamodeltoclosure.model.ConvertedType;
import com.alltheducks.javamodeltoclosure.model.builder.ConvertedTypeBuilder;
import com.alltheducks.javamodeltoclosure.translator.TypeTranslator;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;

public class ClosureTypeTranslator extends TypeTranslator {

    public static final Map<TypeToken<?>,String> PRIMITIVE_TRANSLATIONS;
    public static final Map<TypeToken<?>,String> CLASS_TRANSLATIONS;
    static {
        PRIMITIVE_TRANSLATIONS = new HashMap<TypeToken<?>,String>();
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(List.class), "Array");
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(String.class), "string");
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(Character.class), "string");
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(char.class), "string");
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(Boolean.class), "boolean");
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(boolean.class), "boolean");
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(Number.class), "number");
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(byte.class), "number");
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(short.class), "number");
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(int.class), "number");
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(long.class), "number");
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(float.class), "number");
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(double.class), "number");

        CLASS_TRANSLATIONS = new HashMap<TypeToken<?>,String>();
        CLASS_TRANSLATIONS.put(TypeToken.of(Date.class), "goog.date.DateTime");
        CLASS_TRANSLATIONS.put(TypeToken.of(Map.class), "goog.structs.Map");

    }

    @Override
    public ConvertedType translate(Field field) throws TranslationException {
        try {
            return this.buildType(TypeToken.of(field.getGenericType())).getConvertedType();
        } catch (Exception e) {
            throw new TranslationException("Failed to translate Field to type.", e);
        }
    }

    private ConvertedTypeBuilder buildType(TypeToken<?> typeToken) throws Exception {
        return buildType(typeToken, false);
    }

    private ConvertedTypeBuilder buildType(TypeToken<?> typeToken, boolean skipGenerics) throws TranslationException {

        try {

            if (typeToken.isArray()) {
                ConvertedTypeBuilder convertedTypeBuilder = this.buildType(typeToken.getComponentType(), skipGenerics);
                convertedTypeBuilder.prependName("Array.<").appendName(">");
                return convertedTypeBuilder;
            }

            if(!skipGenerics) {
                TypeVariable<? extends Class<?>>[] typeParameters = typeToken.getRawType().getTypeParameters();
                if (typeParameters.length > 0) {

                    ConvertedTypeBuilder convertedTypeBuilder = this.buildType(typeToken, true);
                    convertedTypeBuilder.appendName(".<");

                    convertedTypeBuilder.appendTypeBuilder(this.translateGenericType(typeParameters[0], typeToken, skipGenerics));
                    for(int i = 1; i < typeParameters.length; i++) {
                        convertedTypeBuilder.appendName(",");
                        convertedTypeBuilder.appendTypeBuilder(this.translateGenericType(typeParameters[i], typeToken, skipGenerics));
                    }

                    convertedTypeBuilder.appendName(">");

                    return convertedTypeBuilder;
                }
            }

            if(this.getPackageTypes() != null) {
                for(TypeToken<?> packageType : this.getPackageTypes()) {
                    if(packageType.isAssignableFrom(typeToken)) {
                        String typeName = this.getPackageTranslator().translate(packageType.getRawType().getName());
                        ConvertedTypeBuilder convertedTypeBuilder = new ConvertedTypeBuilder();
                        convertedTypeBuilder.appendName(typeName);
                        convertedTypeBuilder.addRequires(typeName);
                        return convertedTypeBuilder;
                    }
                }
            }

            for(TypeToken<?> primitiveClass : PRIMITIVE_TRANSLATIONS.keySet()) {
                if(primitiveClass.isAssignableFrom(typeToken)) {
                    ConvertedTypeBuilder convertedTypeBuilder = new ConvertedTypeBuilder();
                    convertedTypeBuilder.appendName(PRIMITIVE_TRANSLATIONS.get(primitiveClass));
                    return convertedTypeBuilder;
                }
            }

            for(TypeToken<?> clazz : CLASS_TRANSLATIONS.keySet()) {
                if(clazz.isAssignableFrom(typeToken)) {
                    String typeName = CLASS_TRANSLATIONS.get(clazz);
                    ConvertedTypeBuilder convertedTypeBuilder = new ConvertedTypeBuilder();
                    convertedTypeBuilder.appendName(typeName);
                    convertedTypeBuilder.addRequires(typeName);
                    return convertedTypeBuilder;
                }
            }

        } catch (Exception e) {
            throw new TranslationException("Failed to translate type: " + typeToken.toString(), e);
        }

        throw new TypeDefinitionException("No translation definition for type: " + typeToken.toString());

    }

    private ConvertedTypeBuilder translateGenericType(Type parameterType, TypeToken<?> typeToken, boolean skipGenerics) throws Exception {
        TypeToken<?> newTypeToken = typeToken.resolveType(parameterType);
        return this.buildType(newTypeToken, skipGenerics);
    }


}