package com.td.service.crud.document;

import com.td.model.context.qualifier.DocumentQualifier;
import com.td.model.context.qualifier.SecurityQualifier;
import com.td.model.entity.document.OrderTransport;
import com.td.model.repository.document.DocumentRepository;
import com.td.model.entity.document.OrderDocumentModel;
import com.td.model.security.SecurityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by zerotul on 28.01.15.
 */
@Service
@DocumentQualifier(DocumentQualifier.Type.ORDER)
public class OrderDocumentCRUDService extends AbstractDocumentCRUDService<OrderDocumentModel, DocumentRepository<OrderDocumentModel>> {

    private SecurityService securityService;

    @Inject
    public OrderDocumentCRUDService(@DocumentQualifier(DocumentQualifier.Type.ORDER) DocumentRepository<OrderDocumentModel> dao) {
        super(dao);
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    @Inject
    @SecurityQualifier
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }



}
