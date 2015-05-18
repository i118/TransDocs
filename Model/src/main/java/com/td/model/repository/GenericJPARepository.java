package com.td.model.repository;


import com.td.model.entity.IPersistent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;


/**
 * User: Zerotul
 */
public class GenericJPARepository<T extends IPersistent> implements IRepository<T> {

    @PersistenceContext(name = "TDPersistenceUnit")
    private EntityManager entityManager;

    private final Class<T> modelClass;

    public GenericJPARepository(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    public T saveOrUpdate(T persistent){
      if(persistent==null)return persistent;
      if(persistent.isNew()){
          save(persistent);
          return persistent;
      }else{
          T mergedPersistent = update(persistent);
          return  mergedPersistent;
      }
    }

    @Override
    public void delete(T persistent){
        persistent.setDeleted(true);
        saveOrUpdate(persistent);
    }

    @Override
    public T getModel(UUID objectId){
      return getModel(objectId, modelClass);
    }

    @Override
    public T getModel(UUID objectId, Class<T> clazz){
        return  getEntityManager().find(clazz, objectId);
    }

    @Override
    public T getReference(UUID objectId, Class<T> clazz){
        return getEntityManager().getReference(clazz, objectId);
    }

    @Override
    public T getReference(UUID id){
        return getReference(id, modelClass);
    }

    @Override
    public T update(T persistent){
     return getEntityManager().merge(persistent);
    }

    @Override
    public void save(T persistent){
        getEntityManager().persist(persistent);
    }

    @Override
    public Class<T> getModelClass(){
        return modelClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
