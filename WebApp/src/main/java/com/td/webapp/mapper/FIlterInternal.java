package com.td.webapp.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by konstantinchipunov on 02.12.14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class FilterInternal{
    private String value;

    private String property;

    FilterInternal(){}

    public String getValue() {
        return value;
    }

    public String getProperty() {
        return property;
    }

}