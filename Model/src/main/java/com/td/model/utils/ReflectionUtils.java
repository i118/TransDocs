package com.td.model.utils;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.core.annotation.AnnotationUtils;

import javax.persistence.OneToMany;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by zerotul.
 */
public abstract class ReflectionUtils {

    public static <T extends Annotation> T getFieldAnnotation(PropertyDescriptor descriptor,  Class<T> annotationClass) throws NoSuchFieldException {
        return  AnnotationUtils.getAnnotation(descriptor.getReadMethod(), annotationClass);
    }
}
