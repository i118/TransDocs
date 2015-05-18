package com.td.service.crud;

import com.td.model.repository.IRepository;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.IPersistent;
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
public abstract class AbstractCRUDService<T extends IPersistent, D extends IRepository> implements CRUDService<T, D> {

    protected D repository;

    private ITypeFactory typeFactory;

    public AbstractCRUDService(D repository){
        this.repository = repository;
    }

    public static class ArgumentName{

    }

    @Override
    public T saveOrUpdate(T model){
       return (T) repository.saveOrUpdate(model);
    }

    protected D getRepository() {
        return repository;
    }


    @Override
    public void delete(T persistent){
        repository.delete(persistent);
    }


    @Override
    @Transactional
    public T getModel(UUID id){
        return (T) repository.getModel(id);
    }

    @Override
    @Transactional
    public <D extends IPersistent> D getModel(UUID id, Class<D> clazz){
       return (D) getRepository().getModel(id, clazz);
    }

    @Override
    @Transactional
    public <D extends IPersistent> D getModel(UUID id, String typeName) {
        Class clazz = typeFactory.getClassByType(typeName);
        if(clazz==null) clazz = AbstractModel.class;
        return (D) getModel(id, clazz);
    }

    @Override
    @Transactional
    public T getReference(UUID id){
        return (T) repository.getReference(id);
    }

    @Override
    @Transactional
    public <D extends IPersistent> D getReference(UUID id, Class<D> clazz){
        return (D) repository.getReference(id, clazz);
    }

    @Override
    @Transactional
    public <D extends IPersistent> D getReference(UUID id, String typeName){
        Class clazz = typeFactory.getClassByType(typeName);
        if(clazz==null) clazz = AbstractModel.class;
        return (D) getReference(id, clazz);
    }

    @Override
    public T update(T persistent) {
        return (T) getRepository().update(persistent);
    }

    @Override
    public void save(T persistent) {

    }

    @Override
    @Transactional
    public <V extends IPersistent> void initLazy(V persistent, LazyInitVisiter<V> visiter){
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
