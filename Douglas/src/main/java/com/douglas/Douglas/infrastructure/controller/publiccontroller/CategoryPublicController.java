package com.douglas.Douglas.infrastructure.controller.publiccontroller;

import com.douglas.Douglas.core.application.CategoryApplication;
import com.douglas.Douglas.infrastructure.dto.CategoryRest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/category")
@RequiredArgsConstructor
public class CategoryPublicController {

    private final CategoryApplication categoryApplication;

    @GetMapping
    public List<CategoryRest> findAll(){
        return categoryApplication.findAll();
    }
}
