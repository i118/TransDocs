package com.td.model.context;

import com.googlecode.flyway.core.Flyway;
import com.td.model.configuration.ApplicationConfigService;
import com.td.model.configuration.CompanyInstaller;
import com.td.model.configuration.FlywayCompanyInstaller;
import com.td.model.context.qualifier.CompanyInstallQualifier;
import com.td.model.context.qualifier.SecurityQualifier;
import com.td.model.entity.dictionary.user.IPasswordEncoder;
import com.td.model.entity.dictionary.user.PasswordEncoderImpl;
import com.td.model.security.SecurityService;
import com.td.model.security.SpringSecurityService;
import com.td.model.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import java.util.Properties;

/**
 * Created by zerotul on 02.04.15.
 */
@Configuration
@PropertySource("classpath:/config/model/ApplicationConfig.properties")
public class ModelServiceContext {

    @Autowired
    @Qualifier("flyway")
    private Flyway flyway;

    @Value("${isEmbeddedDB}")
    private String isEmbeddedDB;

    @Bean
    public IPasswordEncoder passwordEncoder(){
        PasswordEncoderImpl passwordEncoder = PasswordEncoderImpl.getInstance();
        passwordEncoder.setEncoder(passwordEncoderInternal());
        return passwordEncoder;
    }

    @Bean
    public PasswordEncoder passwordEncoderInternal(){
        return new ShaPasswordEncoder(1);
    }

    @Bean
    public ValidationService validationService(){
        return ValidationService.getInstance();
    }

    @Bean
    @SecurityQualifier
    public SecurityService springSecurityService(){
       return new SpringSecurityService();
    }

    @Bean
    @CompanyInstallQualifier
    public CompanyInstaller companyInstaller(){
        return new FlywayCompanyInstaller(flyway);
    }

    @Bean
    public ApplicationConfigService applicationConfigService(){
        Properties properties = new Properties();
        properties.put("isEmbeddedDB",isEmbeddedDB );
        return new ApplicationConfigService(properties);
    }

    @Bean
    public SpringContextHolder springContextHolder(){
        return new SpringContextHolder();
    }
}
