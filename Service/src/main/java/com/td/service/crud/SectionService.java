package com.td.service.crud;

import com.td.model.entity.IModel;

import java.util.List;

/**
 * Created by zerotul.
 */
public interface SectionService<T extends IModel> {

    public List<T> findAll();
}
