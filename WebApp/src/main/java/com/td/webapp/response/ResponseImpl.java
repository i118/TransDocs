package com.td.webapp.response;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by konstantinchipunov on 25.04.14.
 */
public class ResponseImpl<T> implements IResponse<T> {

    private boolean success;

    private String message;

    private Collection<T> results;

    public ResponseImpl() {
        this.success = false;
        this.results = new ArrayList<T>();
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public Collection<T> getResults() {
        return results;
    }

    @Override
    public void addResults(Collection<T> Results) {
        this.results.addAll(Results);
    }

    @Override
    public void addResult(T Result) {
      results.add(Result);
    }

    @Override
    public void removeResult(T Result){
      results.remove(Result);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
