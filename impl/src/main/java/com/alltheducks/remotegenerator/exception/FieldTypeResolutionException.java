package com.alltheducks.remotegenerator.exception;

public class FieldTypeResolutionException extends Exception {

    public FieldTypeResolutionException() {
    }

    public FieldTypeResolutionException(String message) {
        super(message);
    }

    public FieldTypeResolutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldTypeResolutionException(Throwable cause) {
        super(cause);
    }

    public FieldTypeResolutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
