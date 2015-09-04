package com.td.service.crud.dictionary;

import com.td.model.entity.dictionary.AbstractDictionary;
import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.model.repository.IRepository;
import com.td.model.repository.dictionary.DictionaryRepository;
import com.td.model.utils.PagingList;
import com.td.service.crud.GenericCRUDService;
import org.zerotul.specification.Specification;

import java.util.Map;

/**
 * Created by zerotul.
 */
public class GenericDictionaryCRUDService<T extends AbstractDictionary> extends GenericCRUDService<T> implements DictionaryCRUDService<T> {


    public GenericDictionaryCRUDService(DictionaryRepository<T> repository) {
        super(repository);
    }


    @Override
    public <U extends DictionaryDataSet> PagingList<U> findDataSet(Specification<? super U> specification) {
        return getRepository().findDataSet(specification);
    }

    @Override
    protected DictionaryRepository<T> getRepository() {
        return (DictionaryRepository<T>) super.getRepository();
    }
}
