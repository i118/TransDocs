package com.td.webapp.response;

/**
 * Created by zerotul on 17.03.15.
 */
public interface PagingResponse<T> extends IResponse<T>{

    public int getTotalCount();
}
