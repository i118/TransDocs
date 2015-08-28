package com.td.service.crud.dictionary.contractor;

import com.td.model.repository.dictionary.contractor.ContractorRepository;
import com.td.model.entity.dictionary.Dictionary;
import com.td.model.entity.dictionary.company.Contractor;
import com.td.model.entity.dictionary.company.JuridicalPerson;
import com.td.service.crud.dictionary.DictionaryCRUDService;

/**
 * Created by zerotul.
 */
public interface ContractorCRUDService<T extends Contractor & Dictionary & JuridicalPerson>  extends DictionaryCRUDService<T> {


}
