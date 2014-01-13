package com.alltheducks.remotegenerator.exception;

public class TranslationException extends Exception {

    public TranslationException() {
    }

    public TranslationException(String message) {
        super(message);
    }

    public TranslationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TranslationException(Throwable cause) {
        super(cause);
    }

    public TranslationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
