package com.alltheducks.javamodeltoclosure.closure;

import com.alltheducks.javamodeltoclosure.exception.TranslationException;
import com.alltheducks.javamodeltoclosure.exception.TypeDefinitionException;
import com.alltheducks.javamodeltoclosure.model.ConvertedType;
import com.alltheducks.javamodeltoclosure.translator.TypeTranslator;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;

public class ClosureTypeTranslator implements TypeTranslator {

    public static final Map<TypeToken<?>,String> PRIMITIVE_TRANSLATIONS;
    public static final Map<TypeToken<?>,String> CLASS_TRANSLATIONS;
    static {
        PRIMITIVE_TRANSLATIONS = new HashMap<TypeToken<?>,String>();
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(Map.class), "Map");
        PRIMITIVE_TRANSLATIONS.put(TypeToken.of(List.class), "List");
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

    }

    private Collection<TypeToken<?>> packageTypes;

    @Override
    public ConvertedType translate(Field field) throws TranslationException {
        try {
            return this.buildType(TypeToken.of(field.getGenericType())).getConvertedType();
        } catch (Exception e) {
            throw new TranslationException("Failed to translate Field to type.", e);
        }
    }

    private TypeBuilder buildType(TypeToken<?> typeToken) throws Exception {
        return buildType(typeToken, false);
    }

    private TypeBuilder buildType(TypeToken<?> typeToken, boolean skipGenerics) throws TranslationException {

        try {

            if (typeToken.isArray()) {
                TypeBuilder typeBuilder = this.buildType(typeToken.getComponentType(), skipGenerics);
                typeBuilder.prependName("Array.<").appendName(">");
                return typeBuilder;
            }

            if(!skipGenerics) {
                TypeVariable<? extends Class<?>>[] typeParameters = typeToken.getRawType().getTypeParameters();
                if (typeParameters.length > 0) {

                    TypeBuilder typeBuilder = this.buildType(typeToken, true);
                    typeBuilder.appendName(".<");

                    typeBuilder.appendTypeBuilder(this.translateGenericType(typeParameters[0], typeToken, skipGenerics));
                    for(int i = 1; i < typeParameters.length; i++) {
                        typeBuilder.appendName(",");
                        typeBuilder.appendTypeBuilder(this.translateGenericType(typeParameters[i], typeToken, skipGenerics));
                    }

                    typeBuilder.appendName(">");

                    return typeBuilder;
                }
            }

            if(packageTypes != null) {
                for(TypeToken<?> packageType : packageTypes) {
                    if(packageType.isAssignableFrom(typeToken)) {
                        //TODO: deal with packages.
                        String typeName = packageType.getRawType().getSimpleName();
                        TypeBuilder typeBuilder = new TypeBuilder();
                        typeBuilder.appendName(typeName);
                        typeBuilder.addRequires(typeName);
                        return typeBuilder;
                    }
                }
            }

            for(TypeToken<?> primitiveClass : PRIMITIVE_TRANSLATIONS.keySet()) {
                if(primitiveClass.isAssignableFrom(typeToken)) {
                    TypeBuilder typeBuilder = new TypeBuilder();
                    typeBuilder.appendName(PRIMITIVE_TRANSLATIONS.get(primitiveClass));
                    return typeBuilder;
                }
            }

            for(TypeToken<?> clazz : CLASS_TRANSLATIONS.keySet()) {
                if(clazz.isAssignableFrom(typeToken)) {
                    String typeName = CLASS_TRANSLATIONS.get(clazz);
                    TypeBuilder typeBuilder = new TypeBuilder();
                    typeBuilder.appendName(typeName);
                    typeBuilder.addRequires(typeName);
                    return typeBuilder;
                }
            }

        } catch (Exception e) {
            throw new TranslationException("Failed to translate type: " + typeToken.toString(), e);
        }

        throw new TypeDefinitionException("No translation definition for type: " + typeToken.toString());

    }

    private TypeBuilder translateGenericType(Type parameterType, TypeToken<?> typeToken, boolean skipGenerics) throws Exception {
        TypeToken<?> newTypeToken = typeToken.resolveType(parameterType);
        return this.buildType(newTypeToken, skipGenerics);
    }

    public Collection<TypeToken<?>> getPackageTypes() {
        return packageTypes;
    }

    public void setPackageTypes(Collection<TypeToken<?>> packageTypes) {
        this.packageTypes = packageTypes;
    }

    private class TypeBuilder {

        StringBuilder nameBuilder = new StringBuilder();

        public TypeBuilder prependName(String prepend) {
            nameBuilder.insert(0, prepend);
            return this;
        }
        public TypeBuilder appendName(String append) {
            nameBuilder.append(append);
            return this;
        }

        Set<String> requires = new HashSet<String>();

        public TypeBuilder addRequires(String require) {
            requires.add(require);
            return this;
        }

        public TypeBuilder appendTypeBuilder(TypeBuilder typeBuilder) {
            this.nameBuilder.append(typeBuilder.nameBuilder);
            this.requires.addAll(typeBuilder.requires);
            return this;
        }

        public ConvertedType getConvertedType() {
            ConvertedType convertedType = new ConvertedType();
            convertedType.setName(nameBuilder.toString());
            convertedType.setRequires(requires);
            return convertedType;
        }

    }
}