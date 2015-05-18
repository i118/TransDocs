package com.td.webapp.filter;

import com.td.model.multitenant.SchemaProviderImpl;
import com.td.model.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by zerotul on 24.03.15.
 */
public class SchemaAwareFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                Object principal = auth.getPrincipal();
                if (principal instanceof UserDetailsImpl) {
                    SchemaProviderImpl.setCurrentSchema(((UserDetailsImpl) principal).getSchema());
                }
            }
            filterChain.doFilter(request, response);
        }finally {
            SchemaProviderImpl.setCurrentSchema(null);
        }
    }

    @Override
    public void destroy() {

    }
}
