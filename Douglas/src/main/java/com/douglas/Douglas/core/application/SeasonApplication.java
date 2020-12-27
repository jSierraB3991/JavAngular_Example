package com.douglas.Douglas.core.application;

import com.douglas.Douglas.core.model.Season;
import com.douglas.Douglas.core.model.VideoSeason;
import com.douglas.Douglas.core.service.SeasonService;
import com.douglas.Douglas.infrastructure.dto.SeasonRest;
import com.douglas.Douglas.infrastructure.dto.VideoRest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SeasonApplication extends GenericApplication<SeasonRest, Season> {

    private final SeasonService seasonService;
    private final ModelMapper mapper;

    public SeasonApplication(SeasonService seasonService, ModelMapper mapper){
        super(seasonService);
        this.seasonService = seasonService;
        this.mapper = mapper;
    }

    public void deleteChapter(Integer idSeason, Integer idChapter){
        seasonService.deleteChapter(idSeason, idChapter);
    }

    public SeasonRest addUrlVideo(int idSeason,String urlVideo ) {
        Season season = seasonService.addVideoUrl(idSeason, urlVideo);
        return convertToRest(season);
    }

    public List<VideoRest> findChaptersBySeason(Integer idSeason){
        List<VideoSeason> videos = seasonService.findChaptersBySeason(idSeason);
        return videos.stream()
                .map(video -> VideoRest.builder()
                        .urlVideo(video.getUrlVideo())
                        .id(video.getId()).build())
                .collect(Collectors.toList());
    }

    public List<SeasonRest> findAllBySerie(Integer idSerie){
        List<Season> season = seasonService.findAllBySerie(idSerie);
        return convertToRest(season);
    }

    @Override
    protected SeasonRest convertToRest(Season dto) {
        SeasonRest season =  mapper.map(dto, SeasonRest.class);
        if(Optional.ofNullable(dto.getVideoSeasons()).isPresent())
            season.setVideosUrl(dto.getVideoSeasons().stream()
                    .map(vs -> vs.getUrlVideo())
                    .collect(Collectors.toList()));
        return season;
    }

    @Override
    protected Season convertToDto(SeasonRest rest) {
        Season season = mapper.map(rest, Season.class);
        if(Optional.ofNullable(rest.getVideosUrl()).isPresent())
            season.setVideoSeasons(rest.getVideosUrl().stream()
                    .map(iu -> new VideoSeason(0, iu, season))
                    .collect(Collectors.toList()));
        return season;
    }
}
