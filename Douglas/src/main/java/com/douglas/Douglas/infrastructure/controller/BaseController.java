package com.douglas.Douglas.infrastructure.controller;

import com.douglas.Douglas.core.application.GenericApplication;
import com.douglas.Douglas.core.model.BaseEntity;
import com.douglas.Douglas.infrastructure.dto.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public abstract class BaseController<Rest extends BaseRest, Dto extends BaseEntity> {

    private final GenericApplication<Rest, Dto> application;

    @GetMapping
    public List<Rest> findAll(){
        return application.findAll();
    }

    @GetMapping("/{id}")
    public Rest findById(@PathVariable int id) {
        return application.finById(id);
    }

    @PostMapping
    public Rest save(@RequestBody Rest rest) {
        return application.save(rest);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Rest rest)
    {
        if(rest.getId() != id) {

        }
        application.update(rest);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        application.deleteById(id);
    }
}
