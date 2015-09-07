package com.td.service.crud.dictionary.company;

import com.td.model.annotation.SchemaAware;
import com.td.model.context.qualifier.CompanyQualifier;
import com.td.model.context.qualifier.SecurityQualifier;
import com.td.model.repository.dictionary.CompanyRepository;
import com.td.model.entity.Persistent;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.model.security.SecurityService;
import com.td.model.utils.PagingList;
import com.td.service.context.qualifier.CompanyCrud;
import com.td.service.crud.GenericCRUDService;
import com.td.service.crud.dictionary.GenericDictionaryCRUDService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerotul.specification.Specification;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zerotul on 25.03.15.
 */
@Service
@CompanyCrud
public class CompanyCRUDService extends GenericDictionaryCRUDService<CompanyModel> implements CompanyService{

    private final SecurityService securityService;

    @Inject
    public CompanyCRUDService(@CompanyQualifier CompanyRepository repository, @SecurityQualifier SecurityService securityService) {
        super(repository);
        this.securityService = securityService;
    }

    @Override
    @Transactional
    public CompanyModel getCurrentCompany() {
        return securityService.getCurrentUser().getCompany();
    }


    @Override
    @SchemaAware(resolvedBy = SchemaAware.ResolvedBy.COMPANY_ID)
    public CompanyModel findById(UUID id) {
        return super.findById(id);
    }


    @Override
    @SchemaAware(resolvedBy = SchemaAware.ResolvedBy.COMPANY_ID)
    public CompanyModel getReference(UUID id) {
        return super.getReference(id);
    }

    @Override
    protected CompanyRepository getRepository() {
        return (CompanyRepository) super.getRepository();
    }

}
