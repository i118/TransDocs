package com.td.model.repository.dictionary.contractor;

import com.td.model.repository.dictionary.DictionaryJPARepository;
import com.td.model.entity.dictionary.company.Contractor;
import com.td.model.entity.dictionary.company.JuridicalPersonModel;

/**
 * Created by konstantinchipunov on 21.08.14.
 */
public class ContractorJPARepository<T extends JuridicalPersonModel & Contractor> extends DictionaryJPARepository<T> implements ContractorRepository<T> {

    public ContractorJPARepository(Class<T> modelClass) {
        super(modelClass);
    }

}
