package com.td.service.crud.document;

import com.td.model.entity.document.AbstractDocumentModel;
import com.td.model.entity.document.dataset.DocumentDataSet;
import com.td.model.utils.PagingList;
import com.td.service.crud.LazyInitVisiter;
import org.zerotul.specification.Specification;

import java.util.UUID;

/**
 * Created by zerotul on 10.03.15.
 */
public interface DocumentService<T extends AbstractDocumentModel> {

    public void createDocument(T document);

    public <U extends DocumentDataSet> PagingList<U> findDocumentDataSet(Specification<? super DocumentDataSet> specification);

    public T getDocument(UUID documentId);

    public T getDocument(UUID documentId, LazyInitVisiter<T> lazyInitVisiter);
}