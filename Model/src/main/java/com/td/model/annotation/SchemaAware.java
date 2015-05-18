package com.td.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zerotul on 03.04.15.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SchemaAware {

    ResolvedBy resolvedBy() default ResolvedBy.COMPANY;

    public enum ResolvedBy{
        COMPANY, COMPANY_ID
    }
}
