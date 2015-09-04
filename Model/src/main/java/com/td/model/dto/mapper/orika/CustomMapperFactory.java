package com.td.model.dto.mapper.orika;

import ma.glasnost.orika.Mapper;

import java.util.Collection;

/**
 * Created by zerotul.
 */
public interface CustomMapperFactory {

    NamedCustomMapper getNamedMapper(String name);

    Collection<Mapper> getCustomMappers();
}
