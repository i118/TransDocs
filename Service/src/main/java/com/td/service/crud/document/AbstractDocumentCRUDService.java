package com.td.service.crud.document;

import com.td.model.repository.document.DocumentRepository;
import com.td.model.entity.document.AbstractDocumentModel;
import com.td.model.entity.document.dataset.DocumentDataSet;
import com.td.model.utils.PagingList;
import com.td.service.crud.AbstractCRUDService;
import com.td.service.crud.LazyInitVisiter;
import org.springframework.transaction.annotation.Transactional;
import org.zerotul.specification.Specification;

import java.util.UUID;

/**
 * Created by zerotul on 28.01.15.
 */
public abstract class AbstractDocumentCRUDService<T extends AbstractDocumentModel, D extends DocumentRepository<T>> extends AbstractCRUDService<T, D> implements DocumentService<T> {

    public AbstractDocumentCRUDService(D dao) {
        super(dao);
    }

    @Transactional
    public void createDocument(T document){
        getRepository().saveOrUpdate(document);
    }


    @Transactional
    public T getDocument(UUID documentId){
        return getModel(documentId);
    }

    @Transactional
    public T getDocument(UUID documentId, LazyInitVisiter<T> lazyInitVisiter){
        T document = getDocument(documentId);
        if(lazyInitVisiter!=null){
            lazyInitVisiter.initLazy(document);
        }
        return document;
    }

    @Override
    @Transactional
    public <U extends DocumentDataSet> PagingList<U> findDocumentDataSet(Specification<? super DocumentDataSet> specification) {
        return getRepository().findDataSet(specification);
    }

}
