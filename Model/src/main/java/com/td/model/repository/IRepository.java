package com.td.model.repository;

import com.td.model.entity.IModel;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 06.11.13
 * Time: :19
 * To change this template use File | Settings | File Templates.
 *
 *Базовый интерфейс ДАО
 */
public interface IRepository<T extends IModel> {

    public T saveOrUpdate(T persistent);

    public void delete(T persistent);

    public T getModel(UUID id);

    public T getModel(UUID objectId, Class<T> clazz);

    public T getReference(UUID objectId, Class<T> clazz);

    public T getReference(UUID id);

    public void save(T persistent);

    public T update(T persistent);

    public Class<? extends T> getModelClass();

}
