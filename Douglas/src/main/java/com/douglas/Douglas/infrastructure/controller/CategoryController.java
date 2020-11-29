package com.douglas.Douglas.infrastructure.controller;

import com.douglas.Douglas.core.application.CategoryApplication;
import com.douglas.Douglas.core.model.Category;
import com.douglas.Douglas.infrastructure.dto.CategoryRest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController extends  BaseController<CategoryRest, Category>
{
    public CategoryController(CategoryApplication categoryApplication){
        super(categoryApplication);
    }
}
