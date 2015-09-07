package com.td.model.repository;

import com.td.model.entity.AbstractTransientModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 02.01.14.
 */
public class GenericCollectionRepository<T extends AbstractTransientModel> implements IRepository<T>, SectionRepository<T>{

    private List<T> models;

    private Map<UUID, T> modelMap;

    public GenericCollectionRepository(List<T> models){
        this.models = models;
    }

    public void init(){
        modelMap = Collections.synchronizedMap(new HashMap<UUID, T>());
        for (T model : models){
            modelMap.put(model.getObjectId(), model);
        }
    }

    public List<T> findAll(){
        return models;
    }

    @Override
    public T saveOrUpdate(T persistent) {
        if(persistent==null)return persistent;
        int index = models.indexOf(persistent);
        if(index!=-1){
            models.remove(persistent);
            models.add(index, persistent);
        }else{
            models.add(persistent);
        }
        modelMap.put(persistent.getObjectId(), persistent);
        return persistent;
    }

    @Override
    public void delete(T persistent) {
        models.remove(persistent);
    }

    @Override
    public T findById(UUID id) {
        if(id==null)return null;
        return modelMap.get(id);
    }

    @Override
    public T findById(UUID objectId, Class<T> clazz) {
        return findById(objectId);
    }

    @Override
    public T getReference(UUID objectId, Class<T> clazz) {
        return findById(objectId);
    }

    @Override
    public T getReference(UUID id) {
        return findById(id);
    }

    @Override
    public void save(T persistent) {
        if(persistent==null)return;
        models.add(persistent);
        modelMap.put(persistent.getObjectId(), persistent);
    }

    @Override
    public T update(T persistent) {
       return saveOrUpdate(persistent);
    }

    @Override
    public Class<? extends T> getModelClass() {
        throw new UnsupportedOperationException();
    }
}
