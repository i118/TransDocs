package com.td.webapp.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.webapp.controller.file.FileUpload;
import com.td.webapp.mapper.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zerotul on 02.04.15.
 */
@EnableWebMvc
@Configuration
@ComponentScan({"com.td.webapp"})
public class WebMvcContext extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("web/**").addResourceLocations("/web/");
        registry.addResourceHandler("extjs/**").addResourceLocations("/extjs/");
        registry.addResourceHandler("resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(resourceHttpMessageConverter());
        converters.add(jackson2HttpMessageConverter());
    }

    @Bean
    public ObjectMapper hibernateObjectMapper(){
        return new HibernateAwareObjectMapper();
    }

    @Bean
    protected MappingJackson2HttpMessageConverter jackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(hibernateObjectMapper());
        return converter;
    }

    @Bean
    protected ResourceHttpMessageConverter resourceHttpMessageConverter(){
        ResourceHttpMessageConverter converter = new ResourceHttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        converter.setSupportedMediaTypes(mediaTypes);
        return converter;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver =  new CommonsMultipartResolver();
        resolver.setMaxUploadSize(1024 * 1024 * 1024 * 50);
        return resolver;
    }

    @Bean
    @Scope("prototype")
    public FileUpload fileUpload(){
        return new FileUpload();
    }

}
