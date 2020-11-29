package com.douglas.Douglas.infrastructure.data.adapter;

import com.douglas.Douglas.core.model.Category;
import com.douglas.Douglas.core.service.CategoryService;
import com.douglas.Douglas.infrastructure.data.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryAdapter extends GenericAdapter<Category> implements CategoryService {

    public CategoryAdapter(CategoryRepository repository, ModelMapper mapper){
        super(repository, mapper);
    }
}
