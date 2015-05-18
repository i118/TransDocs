package com.td.model.repository.context;

import com.td.model.context.SpringContextHolder;
import com.td.model.context.qualifier.SecurityQualifier;
import com.td.model.entity.dictionary.user.IPasswordEncoder;
import com.td.model.entity.dictionary.user.PasswordEncoderImpl;
import com.td.model.security.SecurityService;
import com.td.model.validation.ValidationService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import javax.inject.Inject;

import static org.mockito.Mockito.mock;

/**
 * Created by zerotul on 03.04.15.
 */
@Configurable
public class ServiceTestContext {

    @Bean
    public IPasswordEncoder passwordEncoder() {
        PasswordEncoderImpl passwordEncoder = PasswordEncoderImpl.getInstance();
        passwordEncoder.setEncoder(passwordEncoderInternal());
        return passwordEncoder;
    }

    @Bean
    public PasswordEncoder passwordEncoderInternal() {
        return new ShaPasswordEncoder(1);
    }

    @Bean
    public ValidationService validationService() {
        return ValidationService.getInstance();
    }

    @Bean
    @Inject
    @SecurityQualifier
    @DependsOn("springContextHolder")
    public SecurityService springSecurityService() {
        SecurityService securityService = mock(SecurityService.class);
        return securityService;
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
