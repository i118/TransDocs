package com.td.service.context;

import com.td.model.context.qualifier.ContractorQualifier;
import com.td.model.context.qualifier.DictionaryQualifier;
import com.td.model.entity.dictionary.SimpleDictionary;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.CustomerModel;
import com.td.model.repository.IRepository;
import com.td.model.repository.dictionary.DictionaryRepository;
import com.td.service.context.qualifier.ContractorCrud;
import com.td.service.context.qualifier.DictionaryCrud;
import com.td.service.crud.CRUDService;
import com.td.service.crud.GenericCRUDService;
import com.td.service.crud.dictionary.DictionaryCRUDService;
import com.td.service.crud.dictionary.GenericDictionaryCRUDService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

/**
 * Created by zerotul.
 */
@Configuration
public class CRUDServiceContext {

    @Bean
    @Inject
    @ContractorCrud(ContractorCrud.Type.CUSTOMER)
    public CRUDService<CustomerModel> customerCRUDService(@ContractorQualifier(ContractorQualifier.Type.CUSTOMER) DictionaryRepository<CustomerModel> repository){
        return new GenericDictionaryCRUDService<>(repository);
    }

    @Bean
    @Inject
    @ContractorCrud(ContractorCrud.Type.CARRIER)
    public CRUDService<CarrierModel> carrierCRUDService(@ContractorQualifier(ContractorQualifier.Type.CARRIER) DictionaryRepository<CarrierModel> repository){
        return new GenericDictionaryCRUDService<>(repository);
    }

    @Bean
    @Inject
    @DictionaryCrud
    public DictionaryCRUDService<SimpleDictionary> simpleDictionaryDictionaryService(@DictionaryQualifier DictionaryRepository<SimpleDictionary> repository){
        return new GenericDictionaryCRUDService<>(repository);
    }
}
