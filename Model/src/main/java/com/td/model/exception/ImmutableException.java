package com.td.model.exception;

/**
 * Created by konstantinchipunov on 02.09.14.
 */
public class ImmutableException extends Exception{

    public ImmutableException() {
    }

    public ImmutableException(String message) {
        super(message);
    }

    public ImmutableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImmutableException(Throwable cause) {
        super(cause);
    }

    public ImmutableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
