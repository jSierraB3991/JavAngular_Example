package com.douglas.Douglas.core.service;

import com.douglas.Douglas.core.model.Season;

import java.util.List;

public interface SeasonService extends GenericService<Season> {

    Season addVideoUrl(int season, String urlVideo);

    List<Season> findAllBySerie(Integer idSerie);
}
