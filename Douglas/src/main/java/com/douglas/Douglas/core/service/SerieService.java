package com.douglas.Douglas.core.service;

import com.douglas.Douglas.core.model.Season;
import com.douglas.Douglas.core.model.Serie;

public interface SerieService extends GenericService<Serie> {

    Serie addSeason(int idSerie, Season season);

    Serie addImageUrl(int idSerie, String urlImage);
}
