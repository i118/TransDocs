package com.td.model.dto.mapper.orika;

import ma.glasnost.orika.CustomMapper;

/**
 * Created by zerotul.
 */
public abstract class NamedCustomMapper<A, B> extends CustomMapper<A, B> {

    public abstract String getName();
}
