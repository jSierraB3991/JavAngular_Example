package com.douglas.Douglas.infrastructure.controller;

import com.douglas.Douglas.core.application.GenericApplication;
import com.douglas.Douglas.core.model.BaseEntity;
import com.douglas.Douglas.infrastructure.dto.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Rest save(@RequestBody @Valid Rest rest) {
        return application.save(rest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(@PathVariable int id, @Valid @RequestBody Rest rest) {
        if(rest.getId() != id) throw new RuntimeException("F Bro...");
        application.update(rest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable int id) {
        application.deleteById(id);
    }
}
