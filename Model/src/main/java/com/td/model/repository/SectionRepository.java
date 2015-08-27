package com.td.model.repository;

import com.td.model.entity.Model;

import java.util.List;

/**
 * Created by zerotul.
 */
public interface SectionRepository<T extends Model> {

    public List<T> findAll();
}
