package com.td.model.utils;

import java.util.List;

/**
 * Created by zerotul on 17.03.15.
 */
public interface PagingList<T> extends List<T> {

    public int getTotalCount();
}
