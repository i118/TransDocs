package com.td.model.repository;

import com.td.model.entity.IModel;

import java.util.List;

/**
 * Created by zerotul.
 */
public interface SectionRepository<T extends IModel> {

    public List<T> findAll();
}
