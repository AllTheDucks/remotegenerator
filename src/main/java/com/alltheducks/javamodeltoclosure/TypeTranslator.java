package com.alltheducks.javamodeltoclosure;

import com.google.common.reflect.TypeToken;

import java.util.Collection;

public interface TypeTranslator {

    String translate(TypeToken<?> typeToken) throws Exception;

}
