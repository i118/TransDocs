package com.td.model.repository.context;

import com.td.model.context.qualifier.SecurityQualifier;
import com.td.model.context.qualifier.UserQualifier;
import com.td.model.repository.dictionary.user.UserRepository;
import com.td.model.multitenant.SchemaProviderImpl;
import com.td.model.security.SecurityService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.inject.Inject;

import static org.mockito.Mockito.when;

/**
 * Created by zerotul.
 */
public class RefreshListener implements ApplicationListener<ContextRefreshedEvent> {

    @Inject
    @SecurityQualifier
    private SecurityService securityService;

    @Inject
    @UserQualifier
    private UserRepository userModelDao;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        SchemaProviderImpl.setCurrentSchema("td_admin");
       when(securityService.getCurrentUser()).thenReturn(userModelDao.getUserByName("admin"));
    }
}
