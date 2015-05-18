package com.td.model.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.zerotul.specification.mapper.Mapper;

import java.io.Serializable;

/**
 * Created by zerotul on 19.03.15.
 */
public interface RowMapperAdapter<T extends Serializable> extends Mapper<T>, RowMapper<T> {
}
