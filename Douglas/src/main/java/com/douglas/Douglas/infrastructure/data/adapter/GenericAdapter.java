package com.douglas.Douglas.infrastructure.data.adapter;

import com.douglas.Douglas.core.model.BaseEntity;
import com.douglas.Douglas.core.service.GenericService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@AllArgsConstructor
public class GenericAdapter<Dto extends BaseEntity> implements GenericService<Dto>
{
    protected final JpaRepository<Dto, Integer> repository;

    @Override
    public List<Dto> findAll() {
        return repository.findAll();
    }

    @Override
    public Dto findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid id " + id));
    }

    @Override
    public Dto save(Dto entity) {
        return repository.save(entity);
    }

    @Override
    public Dto update(Dto entity) {
        findById(entity.getId());
        return save(entity);
    }

    @Override
    public void deleteById(int id) {
        findById(id);
        repository.deleteById(id);
    }
}
