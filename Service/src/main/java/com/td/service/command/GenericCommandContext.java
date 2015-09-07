package com.td.service.command;

import com.td.service.command.exception.CommandException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zerotul.
 */
public class GenericCommandContext <T> implements CommandContext<T> {

    private final T target;

    private Result result;

    private CommandException exception;

    private Map<String, Object> arguments;

    public GenericCommandContext(T target) {
        arguments = new HashMap<>();
        this.target = target;
        this.result = Result.SUCCESS;
    }

    @Override
    public Result getResult() {
        return result;
    }

    @Override
    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public T getTarget() {
        return target;
    }

    @Override
    public CommandException getException() {
        return exception;
    }

    @Override
    public void setException(CommandException ex) {
        this.exception = ex;
    }

}
