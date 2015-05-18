package com.td.model.factory;

import com.td.model.repository.mapper.RowMapperAdapter;

import java.util.Collections;
import java.util.Map;

/**
 * Created by zerotul on 13.03.15.
 */
public class DataSetSqlMapperFactory implements DataSetMapperFactory{

    private Map<Class, RowMapperAdapter> mappers;

    public DataSetSqlMapperFactory(Map<Class, RowMapperAdapter> mappers) {
        this.mappers = Collections.unmodifiableMap(mappers);
    }

    @Override
    public RowMapperAdapter findMapper(Class clazz){
        RowMapperAdapter mapper = mappers.get(clazz);
        if(mapper==null) throw new IllegalStateException("mapper by key "+clazz+" not found");
        return mapper;
    }
}
