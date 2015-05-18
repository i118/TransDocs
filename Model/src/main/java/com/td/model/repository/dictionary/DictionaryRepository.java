package com.td.model.repository.dictionary;

import com.td.model.repository.IRepository;
import com.td.model.entity.dictionary.Dictionary;
import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.model.utils.PagingList;
import org.zerotul.specification.Specification;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 21.11.13
 * Time: 19:39
 * To change this template use File | Settings | File Templates.
 */
public interface DictionaryRepository<T extends Dictionary> extends IRepository<T> {

    public <V extends DictionaryDataSet> PagingList<V> findDataSet(Specification<? super V> specification);

}
