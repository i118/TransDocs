package com.td.webapp.context;

import com.td.service.auth.UserDetailsServiceImpl;
import com.td.service.auth.UserManager;
import com.td.service.auth.UserManagerImpl;
import com.td.service.context.qualifier.PermissionEvaluatorQualifier;
import com.td.service.context.qualifier.UserCrud;
import com.td.service.context.qualifier.UserDetailsQualifier;
import com.td.service.context.qualifier.UserManagerQualifier;
import com.td.service.crud.dictionary.user.UserCRUDService;
import com.td.service.permit.PermissionEvaluatorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import javax.inject.Inject;

/**
 * Created by zerotul on 02.04.15.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityContext extends WebSecurityConfigurerAdapter {

    @Inject
    @UserDetailsQualifier
    private UserDetailsService userDetailsService;

    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers()
                .contentTypeOptions()
                .xssProtection()
                .cacheControl()
                .httpStrictTransportSecurity()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
        http.csrf().disable().authorizeRequests()
                .antMatchers("/app/login.jsp").access("isAnonymous()")
                .antMatchers("/admin.jsp").access("hasAnyRole('ROLE_SUPER_ADMIN')")
                .antMatchers("/CompanyController/update.object/**").access("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER', 'ROLE_SUPER_ADMIN')")
                .antMatchers("/CompanyController/get.object/current").access("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER', 'ROLE_SUPER_ADMIN')")
                .antMatchers("/CompanyController/**").access("hasAnyRole('ROLE_SUPER_ADMIN')")
                .antMatchers("/AdminController/**").access("hasAnyRole('ROLE_SUPER_ADMIN')")
                .antMatchers("/**").access("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER', 'ROLE_SUPER_ADMIN')")
                .and().formLogin()
                .loginPage("/app/login.jsp")
                .loginProcessingUrl("/static/j_spring_security_check")
                .failureUrl("/app/login.jsp")
                .defaultSuccessUrl("/index.jsp").and().logout().logoutUrl("/j_spring_security_logout").invalidateHttpSession(true)
                .logoutSuccessUrl("/app/login.jsp")
                .and()
                .httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/extjs/**", "/app/file-transfer.jar", "/resources/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        ReflectionSaltSource rss = new ReflectionSaltSource();
        rss.setUserPropertyToUse("currentUser");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setSaltSource(rss);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new ShaPasswordEncoder(1));
        auth.authenticationProvider(provider);
    }
}
