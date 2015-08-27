package com.td.service.crud;

import com.td.model.repository.IRepository;
import com.td.model.entity.Persistent;
import com.td.model.factory.ITypeFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 09.11.13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
public class GenericCRUDService<T extends Persistent> implements CRUDService<T> {

    protected IRepository<T> repository;

    private ITypeFactory typeFactory;

    public GenericCRUDService(IRepository<T> repository){
        this.repository = repository;
    }

    public static class ArgumentName{

    }

    @Override
    public T saveOrUpdate(T model){
       return (T) repository.saveOrUpdate(model);
    }

    protected IRepository<T> getRepository() {
        return repository;
    }


    @Override
    public void delete(T persistent){
        repository.delete(persistent);
    }


    @Override
    @Transactional
    public T findById(UUID id){
        return (T) repository.getModel(id);
    }


    @Override
    @Transactional
    public <D extends Persistent> D findById(UUID id, String typeName) {
        Class clazz = typeFactory.getClassByType(typeName);
        if(clazz==null){
            throw new IllegalStateException("entity the type "+typeName+" is not found");
        };
        return (D) getRepository().getModel(id, clazz);
    }

    @Override
    @Transactional
    public T getReference(UUID id){
        return (T) repository.getReference(id);
    }


    @Override
    @Transactional
    public <D extends Persistent> D getReference(UUID id, String typeName){
        Class clazz = typeFactory.getClassByType(typeName);
        if(clazz==null){
            throw new IllegalStateException("entity the type "+typeName+" is not found");
        };
        return (D) getRepository().getReference(id, clazz);
    }

    @Override
    public T update(T persistent) {
        return (T) getRepository().update(persistent);
    }

    @Override
    public void save(T persistent) {
        getRepository().save(persistent);
    }

    @Override
    @Transactional
    public <V extends Persistent> void initLazy(V persistent, LazyInitVisiter<V> visiter){
       persistent = (V) update((T) persistent);
       visiter.initLazy(persistent);
    }

    public ITypeFactory getTypeFactory() {
        return typeFactory;
    }

    @Inject
    public void setTypeFactory(ITypeFactory typeFactory) {
        this.typeFactory = typeFactory;
    }
}
