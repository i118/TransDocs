package com.td.model.context;

import com.td.model.aop.DTODirtyFieldsAspect;
import com.td.model.aop.SchemaAwareAspect;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/**
 * Created by zerotul on 03.04.15.
 */
@Configuration
@EnableSpringConfigured
@EnableAspectJAutoProxy
public class ModelAopContext {

    @Bean
    public AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator() {
        return new AnnotationAwareAspectJAutoProxyCreator();
    }

    @Bean
    public SchemaAwareAspect schemaAwareAspect() {
        return SchemaAwareAspect.aspectOf();
    }

    @Bean
    public DTODirtyFieldsAspect dtoDirtyFieldsAspect() {
        return DTODirtyFieldsAspect.aspectOf();
    }
}
