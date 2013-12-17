package com.alltheducks.javamodeltoclosure.exception;

public class TypeTranslationException extends Exception {

    public TypeTranslationException() {
    }

    public TypeTranslationException(String message) {
        super(message);
    }

    public TypeTranslationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeTranslationException(Throwable cause) {
        super(cause);
    }

    public TypeTranslationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
