package com.douglas.Douglas.infrastructure.data.adapter;

import com.douglas.Douglas.core.model.Season;
import com.douglas.Douglas.core.model.VideoSeason;
import com.douglas.Douglas.core.service.SeasonService;
import com.douglas.Douglas.infrastructure.data.repository.SeasonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SeasonAdapter extends GenericAdapter<Season> implements SeasonService {

    public SeasonAdapter(SeasonRepository seasonRepository, ModelMapper mapper){
        super(seasonRepository, mapper);
    }

    @Override
    public Season addVideoUrl(int idSeason, String urlVideo) {
        Season season = findById(idSeason);
        if(!Optional.ofNullable(season.getVideoSeasons()).isPresent())
            season.setVideoSeasons(new ArrayList<>());
        season.getVideoSeasons().add(new VideoSeason(0, urlVideo, season));
        update(season);
        return season;
    }
}
