package com.alltheducks.javamodeltoclosure.model.builder;

import com.alltheducks.javamodeltoclosure.model.ConvertedType;

import java.util.HashSet;
import java.util.Set;


public class ConvertedTypeBuilder {

    StringBuilder nameBuilder = new StringBuilder();

    public ConvertedTypeBuilder prependName(String prepend) {
        nameBuilder.insert(0, prepend);
        return this;
    }
    public ConvertedTypeBuilder appendName(String append) {
        nameBuilder.append(append);
        return this;
    }

    Set<String> requires = new HashSet<String>();

    public ConvertedTypeBuilder addRequires(String require) {
        requires.add(require);
        return this;
    }

    public ConvertedTypeBuilder appendTypeBuilder(ConvertedTypeBuilder convertedTypeBuilder) {
        this.nameBuilder.append(convertedTypeBuilder.nameBuilder);
        this.requires.addAll(convertedTypeBuilder.requires);
        return this;
    }

    public ConvertedType getConvertedType() {
        ConvertedType convertedType = new ConvertedType();
        convertedType.setName(nameBuilder.toString());
        convertedType.setRequires(requires);
        return convertedType;
    }

}
