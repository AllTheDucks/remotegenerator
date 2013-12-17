package com.alltheducks.javamodeltoclosure.exception;

public class TypeDefinitionException extends TranslationException {

    public TypeDefinitionException() {
    }

    public TypeDefinitionException(String message) {
        super(message);
    }

    public TypeDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeDefinitionException(Throwable cause) {
        super(cause);
    }

    public TypeDefinitionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
