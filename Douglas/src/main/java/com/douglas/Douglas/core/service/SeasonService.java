package com.douglas.Douglas.core.service;

import com.douglas.Douglas.core.model.Season;
import com.douglas.Douglas.core.model.VideoSeason;

import java.util.List;

public interface SeasonService extends GenericService<Season> {

    Season addVideoUrl(int season, String urlVideo);

    void deleteChapter(Integer idSeason, Integer idChapter);

    List<Season> findAllBySerie(Integer idSerie);

    List<VideoSeason> findChaptersBySeason(Integer idSeason);
}
