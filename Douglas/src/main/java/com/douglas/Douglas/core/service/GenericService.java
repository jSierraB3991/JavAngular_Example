package com.douglas.Douglas.core.service;

import com.douglas.Douglas.core.model.BaseEntity;

import java.util.List;

public interface GenericService<model extends BaseEntity>
{
    List<model> findAll();

    model findById(int id);

    model save(model entity);

    model update(model entity);

    void deleteById(int id);
}
