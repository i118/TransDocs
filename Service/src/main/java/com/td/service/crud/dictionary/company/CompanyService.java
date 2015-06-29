package com.td.service.crud.dictionary.company;

import com.td.model.repository.dictionary.CompanyRepository;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.service.crud.CRUDService;
import com.td.service.crud.dictionary.DictionaryCRUDService;

/**
 * Created by zerotul on 25.03.15.
 */
public interface CompanyService extends DictionaryCRUDService<CompanyModel>, CRUDService<CompanyModel> {

    public CompanyModel getCurrentCompany();
}
