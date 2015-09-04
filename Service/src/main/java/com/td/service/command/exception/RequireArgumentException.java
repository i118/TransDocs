package com.td.service.command.exception;

/**
 * Created by zerotul.
 */
public class RequireArgumentException extends RuntimeException {
    public RequireArgumentException() {
    }

    public RequireArgumentException(String message) {
        super(message);
    }

    public RequireArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequireArgumentException(Throwable cause) {
        super(cause);
    }

    public RequireArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
