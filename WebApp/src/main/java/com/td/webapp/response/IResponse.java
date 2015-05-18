package com.td.webapp.response;

import java.util.Collection;

/**
 * Created by konstantinchipunov on 25.04.14.
 */
public interface IResponse<T> {

    public boolean isSuccess();

    public void setSuccess(boolean success);

    public void addResult(T Result);

    public void addResults(Collection<T> Results);

    public Collection<T> getResults();

    void removeResult(T Result);

    public void setMessage(String message);
}
