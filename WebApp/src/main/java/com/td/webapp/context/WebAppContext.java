package com.td.webapp.context;

import com.td.service.context.ServiceContext;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

/**
 * Created by zerotul on 02.04.15.
 */
@Configuration
@Import({
        ServiceContext.class,
        WebMvcContext.class,
        WebSecurityContext.class
})
public class WebAppContext {
}
