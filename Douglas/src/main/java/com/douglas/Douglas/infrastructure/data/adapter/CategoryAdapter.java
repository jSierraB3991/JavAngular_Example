package com.douglas.Douglas.infrastructure.data.adapter;

import com.douglas.Douglas.core.model.Category;
import com.douglas.Douglas.core.service.CategoryService;
import com.douglas.Douglas.core.service.SerieService;
import com.douglas.Douglas.infrastructure.data.repository.CategoryRepository;
import com.douglas.Douglas.infrastructure.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class CategoryAdapter extends GenericAdapter<Category> implements CategoryService {

    private final SerieService serieService;
    public CategoryAdapter(CategoryRepository repository,
                           SerieService serieService){
        super(repository);
        this.serieService = serieService;
    }

    @Override
    public void deleteById(int id) {
        if(!serieService.findByCategory(id).isEmpty()){
            throw BusinessException.runException("Esta Categoria este siendo usada por una o más series");
        }
        super.deleteById(id);
    }
}
