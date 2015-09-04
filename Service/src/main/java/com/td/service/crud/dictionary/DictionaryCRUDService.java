package com.td.service.crud.dictionary;

import com.td.model.repository.dictionary.DictionaryRepository;
import com.td.model.entity.dictionary.Dictionary;
import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.model.utils.PagingList;
import com.td.service.crud.CRUDService;
import org.zerotul.specification.Specification;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 21.11.13
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
public interface DictionaryCRUDService<T extends Dictionary>  extends CRUDService<T>{

    <U extends DictionaryDataSet> PagingList<U> findDataSet(Specification<? super U> specification);
}
