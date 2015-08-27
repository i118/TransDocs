package com.td.service.crud.document;

import com.td.model.repository.IRepository;
import com.td.model.repository.document.DocumentRepository;
import com.td.model.entity.document.AbstractDocumentModel;
import com.td.model.entity.document.dataset.DocumentDataSet;
import com.td.model.utils.PagingList;
import com.td.service.crud.GenericCRUDService;
import com.td.service.crud.LazyInitVisiter;
import org.springframework.transaction.annotation.Transactional;
import org.zerotul.specification.Specification;

import java.util.UUID;

/**
 * Created by zerotul on 28.01.15.
 */
public class DocumentCRUDService<T extends AbstractDocumentModel> extends GenericCRUDService<T> implements DocumentService<T> {

    public DocumentCRUDService(DocumentRepository<T> repository) {
        super(repository);
    }

    @Transactional
    public void createDocument(T document){
        getRepository().saveOrUpdate(document);
    }


    @Transactional
    public T getDocument(UUID documentId){
        return findById(documentId);
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

    @Override
    protected DocumentRepository<T> getRepository() {
        return (DocumentRepository<T>) super.getRepository();
    }
}
