package com.td.jcr;

import org.springframework.dao.UncategorizedDataAccessException;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public class JcrSystemException extends UncategorizedDataAccessException {

    public JcrSystemException(String message, Throwable ex) {
        super(message, ex);
    }


    public JcrSystemException(Throwable ex) {
        super("Repository access exception", ex);
    }
}
