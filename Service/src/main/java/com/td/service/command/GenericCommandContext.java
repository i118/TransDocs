package com.td.service.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zerotul.
 */
public class GenericCommandContext <T> implements CommandContext<T> {

    private final T target;

    private Result result;

    private Exception exception;

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
    public Exception getException() {
        return exception;
    }

    @Override
    public void setException(Exception ex) {
        this.exception = ex;
    }

    @Override
    public Object getArgument(String key) {
        return getArgument(key, Object.class);
    }

    @Override
    public <V> V getArgument(String key, Class<V> argumentClass) {
        return (V) arguments.get(key);
    }

    @Override
    public void addArgument(String key, Object value) {
        arguments.put(key, value);
    }
}
