package com.douglas.Douglas.core.application;

import com.douglas.Douglas.core.model.Category;
import com.douglas.Douglas.core.service.CategoryService;
import com.douglas.Douglas.infrastructure.dto.CategoryRest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryApplication extends GenericApplication<CategoryRest, Category>
{
    private final ModelMapper mapper;

    public CategoryApplication(CategoryService categoryService, ModelMapper mapper) {
        super(categoryService);
        this.mapper = mapper;
    }

    @Override
    protected CategoryRest convertToRest(Category dto) {
        return mapper.map(dto, CategoryRest.class);
    }

    @Override
    protected Category convertToDto(CategoryRest rest) {
        return mapper.map(rest, Category.class);
    }
}
