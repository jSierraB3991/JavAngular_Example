package com.douglas.Douglas.core.service;

import com.douglas.Douglas.core.model.Season;

public interface SeasonService extends GenericService<Season> {

    Season addVideoUrl(int season, String urlVideo);
}
