package com.td.service.crud;

import com.td.model.dto.DirtySupportDTO;
import com.td.model.dto.ModelDTO;
import com.td.model.entity.Persistent;
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
public interface CRUDService<T extends Persistent>  extends IService{

    T saveOrUpdate(T model);

    void delete(T persistent);

    T findById(UUID id);

    <D extends Persistent> D findById(UUID id, String typeName);

    T getReference(UUID id);

    <D extends Persistent> D getReference(UUID id, String typeName);

    @Transactional(readOnly = true)
    public default T findById(UUID id, LazyInitVisiter<T> visiter){
        T persistent = findById(id);
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

    public  T update(T dto);

    public void save(T persistent);

    public <V extends Persistent> void initLazy(V persistent, LazyInitVisiter<V> visiter);

}
