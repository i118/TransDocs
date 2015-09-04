package com.td.service.context;

import com.td.model.context.ModelContext;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by zerotul on 02.04.15.
 */
@Configuration
@Import({ModelContext.class, PermissionContext.class,CRUDServiceContext.class,  CRUDFacadeContext.class})
@EnableTransactionManagement
@EnableSpringConfigured
@EnableAspectJAutoProxy
@ComponentScan("com.td.service.*")
public class ServiceContext {
}
