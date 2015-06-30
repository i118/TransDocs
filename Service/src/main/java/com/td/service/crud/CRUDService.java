package com.td.service.crud;

import com.td.model.repository.IRepository;
import com.td.model.entity.IPersistent;
import com.td.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 08.11.13
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 */
public interface CRUDService<T extends IPersistent>  extends IService{

    public T saveOrUpdate(T model);

    public void delete(T persistent);

    public T getModel(UUID id);

    public <D extends IPersistent> D getModel(UUID id, Class<D> clazz);

    public <D extends IPersistent> D getModel(UUID id, String typeName);

    public T getReference(UUID id);

    public <D extends IPersistent> D getReference(UUID id, Class<D> clazz);

    public <D extends IPersistent> D getReference(UUID id, String typeName);

    @Transactional(readOnly = true)
    public default T getModel(UUID id, LazyInitVisiter<T> visiter){
        T persistent = getModel(id);
        if(visiter!=null){
            visiter.initLazy(persistent);
        }
      return persistent;
    }

    @Transactional(readOnly = true)
    public default T getReference(UUID id, LazyInitVisiter<T> visiter){
        T persistent = getReference(id);
        if(visiter!=null){
            visiter.initLazy(persistent);
        }
      return persistent;
    }

    public  T update(T persistent);

    public void save(T persistent);

    public <V extends IPersistent> void initLazy(V persistent, LazyInitVisiter<V> visiter);

}
