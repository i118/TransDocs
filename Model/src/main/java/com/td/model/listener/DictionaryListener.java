package com.td.model.listener;

import com.td.model.context.qualifier.SecurityQualifier;
import com.td.model.entity.dictionary.AbstractDictionary;
import com.td.model.security.SecurityService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.persistence.PrePersist;

/**
 * Created by zerotul on 07.04.15.
 */
@Component
public class DictionaryListener extends InjectSupportListener{

    @Inject
    @SecurityQualifier
    private SecurityService securityService;

    @PrePersist
    public void persist(AbstractDictionary dictionary){
        dictionary.setOwner(securityService.getCurrentUser());
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
