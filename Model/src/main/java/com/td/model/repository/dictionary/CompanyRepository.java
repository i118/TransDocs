package com.td.model.repository.dictionary;

import com.td.model.entity.dictionary.company.CompanyModel;

/**
 * Created by zerotul on 25.03.15.
 */
public interface CompanyRepository extends DictionaryRepository<CompanyModel> {

    public CompanyModel findByLogin(String login);
}
