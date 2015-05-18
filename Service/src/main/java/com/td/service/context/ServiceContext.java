package com.td.service.context;

import com.td.model.aop.SchemaAwareAspect;
import com.td.model.context.ModelContext;
import com.td.service.auth.UserDetailsServiceImpl;
import com.td.service.auth.UserManager;
import com.td.service.auth.UserManagerImpl;
import com.td.service.context.qualifier.UserCrud;
import com.td.service.context.qualifier.UserDetailsQualifier;
import com.td.service.context.qualifier.UserManagerQualifier;
import com.td.service.crud.dictionary.user.UserCRUDService;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by zerotul on 02.04.15.
 */
@Configuration
@Import({ModelContext.class, PermissionContext.class})
@EnableTransactionManagement
@EnableSpringConfigured
@EnableAspectJAutoProxy
@ComponentScan("com.td.service.*")
public class ServiceContext {
}
