package com.douglas.Douglas.core.service;

import com.douglas.Douglas.core.model.Season;
import com.douglas.Douglas.core.model.Serie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SerieService extends GenericService<Serie> {

    Page<Serie> findAll(Pageable pageable, String user);

    Serie addSeason(int idSerie, Season season);

    Serie addImageUrl(int idSerie, String urlImage);

    List<Serie> findByCategory(int category);

    Serie findById(Integer id, String userName);
}
