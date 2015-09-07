package com.td.model.dto.mapper;

/**
 * Created by zerotul.
 */
public interface Mapper<T, D> {

    void map(T src, D dest);

    <V extends D> V map(Object src, Class<V> clazz);
}
