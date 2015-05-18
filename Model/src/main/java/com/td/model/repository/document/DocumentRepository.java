package com.td.model.repository.document;

import com.td.model.repository.IRepository;
import com.td.model.entity.document.AbstractDocumentModel;
import com.td.model.entity.document.dataset.DocumentDataSet;
import com.td.model.utils.PagingList;
import org.zerotul.specification.Specification;

/**
 * Created by zerotul on 28.01.15.
 */
public interface DocumentRepository<T extends AbstractDocumentModel> extends IRepository<T> {


    public <V extends DocumentDataSet> PagingList<V> findDataSet(Specification<? super V> specification);
}
