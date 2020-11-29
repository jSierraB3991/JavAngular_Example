package com.douglas.Douglas.core.application;

import com.douglas.Douglas.core.model.BaseEntity;
import com.douglas.Douglas.core.service.GenericService;
import com.douglas.Douglas.infrastructure.dto.BaseRest;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class GenericApplication<Rest extends BaseRest, Dto extends BaseEntity>
{
    private final GenericService<Dto> service;

    public List<Rest> findAll(){
        List<Rest> rets = convertToRest(service.findAll());
        return rets;
    }

    public Rest finById(int id) {
        Dto dto = service.findById(id);
        return convertToRest(dto);
    }

    public Rest save(Rest rest) {
        Dto dto = service.save(setSave(rest));
        return convertToRest(dto);
    }

    public Rest update(Rest rest) {
        service.update(setUpdate(rest));
        return rest;
    }

    protected Dto setSave(Rest rest) {
        return convertToDto(rest);
    }

    protected Dto setUpdate(Rest rest) {
        return convertToDto(rest);
    }

    public void deleteById(int id) {
        service.deleteById(id);
    }

    protected List<Rest> convertToRest(List<Dto> dtos) {
        return  dtos.stream().map(this::convertToRest).collect(Collectors.toList());
    }

    protected List<Dto> convertToDto(List<Rest> rests){
        return  rests.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    protected abstract Rest convertToRest(Dto dto);

    protected abstract Dto convertToDto(Rest rest);
}
