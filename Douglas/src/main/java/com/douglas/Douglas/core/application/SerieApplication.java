package com.douglas.Douglas.core.application;

import com.douglas.Douglas.core.model.ImageSerie;
import com.douglas.Douglas.core.model.Season;
import com.douglas.Douglas.core.model.Serie;
import com.douglas.Douglas.core.model.VideoSeason;
import com.douglas.Douglas.core.service.CategoryService;
import com.douglas.Douglas.core.service.SerieService;
import com.douglas.Douglas.infrastructure.dto.CategoryRest;
import com.douglas.Douglas.infrastructure.dto.PageRest;
import com.douglas.Douglas.infrastructure.dto.SeasonRest;
import com.douglas.Douglas.infrastructure.dto.SerieRest;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SerieApplication extends GenericApplication<SerieRest, Serie> {

    private final ModelMapper mapper;
    private final CategoryService categoryService;
    private final SerieService serieService;

    public SerieApplication(SerieService serieService,
                            CategoryService categoryService,
                            ModelMapper mapper) {
        super(serieService);
        this.serieService = serieService;
        this.mapper = mapper;
        this.categoryService = categoryService;
    }

    public SerieRest addSeason(int idSerie, SeasonRest season) {
        Serie serie = serieService.addSeason(idSerie, mapper.map(season, Season.class));
        return convertToRest(serie);
    }

    public SerieRest addImageUrl(int idSerie, String urlImage){
        Serie serie = serieService.addImageUrl(idSerie, urlImage);
        return convertToRest(serie);
    }

    public PageRest<SerieRest> findAll(Pageable pageable, String user) {
        Page<Serie> series = serieService.findAll(pageable, user);
        PageRest<SerieRest> pageRest = new PageRest<>();
        pageRest.setData(convertToRest(series.toList()));
        pageRest.setMaxPage(series.getTotalPages());
        pageRest.setPreviousPage(previousPage(series.getTotalPages(), pageable.getPageNumber()));
        pageRest.setNextPage(nextPage(series.getTotalPages(), pageable.getPageNumber()));
        return pageRest;
    }

    private SerieRest set(SerieRest rest)
    {
        rest.setCategory(mapper.map(categoryService.findById(rest.getCategory().getId()),
                CategoryRest.class));
        return rest;
    }

    @Override
    protected Serie setSave(SerieRest rest) {
        return super.setSave(set(rest));
    }

    @Override
    protected Serie setUpdate(SerieRest rest) {
        return super.setUpdate(set(rest));
    }

    @Override
    protected SerieRest convertToRest(Serie dto) {
        SerieRest serie = mapper.map(dto, SerieRest.class);

        if(Optional.ofNullable(dto.getImageSeries()).isPresent() && !dto.getImageSeries().isEmpty()){
            serie.setImages(dto.getImageSeries().stream()
                    .map(ImageSerie::getUrlImage)
                    .collect(Collectors.toList()));
            serie.setFirstImage(dto.getImageSeries().get(0).getUrlImage());
        }

        if(Optional.ofNullable(dto.getSeasons()).isPresent()) {
            List<VideoSeason> videoSeasonList = new ArrayList<>();
            dto.getSeasons().forEach(season -> videoSeasonList.addAll(season.getVideoSeasons()));

            serie.getSeasons().forEach(season ->{
                    List<String> urlVideos = videoSeasonList.stream()
                            .filter(vs -> vs.getSeason().getId() == season.getId())
                            .map(VideoSeason::getUrlVideo)
                            .collect(Collectors.toList());
                    season.setVideosUrl(urlVideos);
            });
        }
        return serie;
    }

    @Override
    protected Serie convertToDto(SerieRest rest) {
        Serie serie =  mapper.map(rest, Serie.class);
        if(Optional.ofNullable(rest.getImages()).isPresent())
            serie.setImageSeries(
                    rest.getImages().stream()
                            .map(i -> new ImageSerie(0, i, serie))
                            .collect(Collectors.toList())
            );
        return serie;
    }
}
