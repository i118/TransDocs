package com.td.model.repository.dictionary.contractor;

import com.td.model.repository.dictionary.DictionaryRepository;
import com.td.model.entity.dictionary.company.Contractor;
import com.td.model.entity.dictionary.company.JuridicalPerson;

/**
 * Created by zerotul on 19.03.15.
 */
public interface ContractorRepository<T extends JuridicalPerson & Contractor> extends DictionaryRepository<T> {
}
