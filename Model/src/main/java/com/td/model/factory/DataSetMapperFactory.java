package com.td.model.factory;

import com.td.model.repository.mapper.RowMapperAdapter;

/**
 * Created by zerotul on 13.03.15.
 */
public interface DataSetMapperFactory {

    public RowMapperAdapter findMapper(Class clazz);
}
