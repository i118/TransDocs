package com.td.service.crud;

import com.td.model.entity.Model;

import java.util.List;

/**
 * Created by zerotul.
 */
public interface SectionService<T extends Model> {

    public List<T> findAll();
}
