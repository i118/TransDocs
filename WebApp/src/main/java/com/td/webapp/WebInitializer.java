package com.td.webapp;

import com.td.model.context.ModelContext;
import com.td.service.context.ServiceContext;
import com.td.webapp.context.WebAppContext;
import com.td.webapp.context.WebSecurityContext;
import com.td.webapp.filter.SchemaAwareFilter;
import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.EnumSet;


/**
 * Created by zerotul on 02.04.15.
 */
public class WebInitializer implements WebApplicationInitializer {

    Logger logger = Logger.getLogger(getClass());

    @Override
    public void onStartup(ServletContext container) throws ServletException {
            AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
            rootContext.register(WebAppContext.class);
            container.setInitParameter("log4jConfigLocation", "/WEB-INF/log4j.xml");
            container.addListener(new ContextLoaderListener(rootContext));
            container.addListener(new Log4jConfigListener());
            container.addListener(new RequestContextListener());

            FilterRegistration.Dynamic requestContextFilter = container.addFilter("requestContextFilter", RequestContextFilter.class);
            EnumSet<DispatcherType> types = EnumSet.of(DispatcherType.REQUEST, DispatcherType.INCLUDE, DispatcherType.FORWARD);
            requestContextFilter.addMappingForUrlPatterns(types, false, "/*");

            FilterRegistration.Dynamic encodingFilter = container.addFilter("encodingFilter", CharacterEncodingFilter.class);
            encodingFilter.setInitParameter("encoding", "UTF-8");
            encodingFilter.setInitParameter("forceEncoding", "true");
            encodingFilter.addMappingForUrlPatterns(null, false, "/*");

            FilterRegistration.Dynamic springSecurityFilterChain = container.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
            springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");

            FilterRegistration.Dynamic schemaAwareFilter = container.addFilter("schemaAwareFilter", SchemaAwareFilter.class);
            schemaAwareFilter.addMappingForUrlPatterns(null, false, "/*");

            ServletRegistration.Dynamic dispatcher = container.addServlet("transdocs", new DispatcherServlet(rootContext));
            dispatcher.setLoadOnStartup(1);
            dispatcher.addMapping("/");
    }
}
