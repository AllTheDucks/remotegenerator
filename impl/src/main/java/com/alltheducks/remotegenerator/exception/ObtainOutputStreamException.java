package com.alltheducks.remotegenerator.exception;

public class ObtainOutputStreamException extends Exception {

    public ObtainOutputStreamException() {
    }

    public ObtainOutputStreamException(String message) {
        super(message);
    }

    public ObtainOutputStreamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObtainOutputStreamException(Throwable cause) {
        super(cause);
    }

    public ObtainOutputStreamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
