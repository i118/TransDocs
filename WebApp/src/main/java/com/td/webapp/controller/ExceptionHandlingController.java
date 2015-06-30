package com.td.webapp.controller;

import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.OptimisticLockException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by konstantinchipunov on 27.08.14.
 */
@ControllerAdvice
public class ExceptionHandlingController {

    Logger logger = Logger.getLogger(getClass());

    @ExceptionHandler(Throwable.class)
    public @ResponseBody
    IResponse anyError(Throwable e){
        IResponse response = new ResponseImpl<>();
        response.setSuccess(false);
        if(e instanceof ConstraintViolationException){
            prepareConstraintErrorResponse(response, (ConstraintViolationException) e);
            return response;
        }else {
            Throwable cause = e.getCause();
            while (cause != null) {
                if (cause instanceof ConstraintViolationException) {
                    prepareConstraintErrorResponse(response, (ConstraintViolationException) cause);
                    return response;
                }
                cause = cause.getCause();
            }
        }
        prepareAnyErrorResponse(response, e);
        return response;
    }

    @ExceptionHandler(OptimisticLockException.class)
    public @ResponseBody IResponse lockException(Throwable e){
        IResponse response = new ResponseImpl<>();
        response.setSuccess(false);
        response.setMessage("Объект изменен другим пользователем");
        return response;
    }

    protected void prepareConstraintErrorResponse(IResponse response, ConstraintViolationException e){
        StringBuilder message = new StringBuilder();
        int i = 1;
        for (ConstraintViolation violation : e.getConstraintViolations()){
            message.append(i).append(".").append(violation.getMessage()).append("<br>");
            i++;
        }
        response.setMessage(message.toString());
    }

    protected void prepareAnyErrorResponse(IResponse response, Throwable e){
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        logger.error(writer.toString());
        response.setMessage(writer.toString());
    }
}
