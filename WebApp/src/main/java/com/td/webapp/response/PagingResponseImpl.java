package com.td.webapp.response;

/**
 * Created by zerotul on 17.03.15.
 */
public class PagingResponseImpl<T> extends ResponseImpl<T> implements PagingResponse<T> {

    private final int totalCount;

    public PagingResponseImpl(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public int getTotalCount() {
        return totalCount;
    }
}
