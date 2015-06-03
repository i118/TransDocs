package com.td.service.crud.dictionary.contractor;

import com.td.model.context.qualifier.ContractorQualifier;
import com.td.model.context.qualifier.FileQualifier;
import com.td.model.repository.dictionary.contractor.ContractorRepository;
import com.td.model.entity.dictionary.company.CustomerModel;
import com.td.service.context.qualifier.ContractorCrud;
import com.td.service.crud.file.FileService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.td.model.context.qualifier.ContractorQualifier.Type;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by konstantinchipunov on 25.08.14.
 */
@Service
@ContractorCrud(ContractorCrud.Type.CUSTOMER)
public class CustomerCRUDServiceImpl extends ContractorCRUDServiceImpl<CustomerModel, ContractorRepository<CustomerModel>> {

    @Inject
    public CustomerCRUDServiceImpl(@ContractorQualifier(Type.CUSTOMER) ContractorRepository<CustomerModel> dao) {
        super(dao);
    }

    private FileService fileService;


    @Override
    @PreAuthorize(PRE_AUTHORIZE)
    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
    public void createDictionaryObject(CustomerModel object, Map<String, String> args) {
        super.createDictionaryObject(object, args);
        getFileService().saveFiles(object);
    }


    @Inject
    @FileQualifier
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public FileService getFileService() {
        return fileService;
    }
}