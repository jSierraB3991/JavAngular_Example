package com.douglas.Douglas.infrastructure.data.adapter;

import com.douglas.Douglas.core.model.Authorization;
import com.douglas.Douglas.core.model.ImageSerie;
import com.douglas.Douglas.core.model.Season;
import com.douglas.Douglas.core.model.Serie;
import com.douglas.Douglas.core.service.SerieService;
import com.douglas.Douglas.infrastructure.data.repository.AuthorizationRepository;
import com.douglas.Douglas.infrastructure.data.repository.SeasonRepository;
import com.douglas.Douglas.infrastructure.data.repository.SerieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieAdapter extends GenericAdapter<Serie> implements SerieService {

    private final AuthorizationRepository authorizationRepository;
    private  final SerieRepository serieRepository;
    private final SeasonRepository seasonRepository;
    public SerieAdapter(SerieRepository serieRepository,
                        AuthorizationRepository authorizationRepository,
                        SeasonRepository seasonRepository){
        super(serieRepository);
        this.serieRepository = serieRepository;
        this.seasonRepository = seasonRepository;
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public Serie update(Serie entity) {
        Optional<Serie> serioO = serieRepository.findById(entity.getId());
        if(serioO.isPresent()){
            entity.setSeasons(serioO.get().getSeasons());
            entity.setImageSeries(serioO.get().getImageSeries());
        }
        return repository.save(entity);
    }

    @Override
    public Page<Serie> findAll(Pageable pageable, String user) {
        Page<Serie> series = repository.findAll(pageable);
        if(!Optional.ofNullable(user).isPresent()) setVideos(series);
        else{
            Optional<Authorization> userO = authorizationRepository.findByEmail(user);
            if(!userO.isPresent()) setVideos(series);
            if(userO.isPresent() && !userO.get().isStatus()) setVideos(series);
        }
        return series;
    }

    public void setVideos(Page<Serie> series){
        series.get().forEach(serie -> {
            serie.getSeasons().forEach(season -> season.setVideoSeasons(new ArrayList<>()));
        });
    }

    public void setVideos(Serie serie){
        serie.getSeasons().forEach(season -> season.setVideoSeasons(new ArrayList<>()));
    }

    @Override
    public Serie addSeason(int idSerie, Season season) {
        Serie serie = findById(idSerie);
        if(!Optional.ofNullable(serie.getSeasons()).isPresent())
            serie.setSeasons(new ArrayList<>());
        season.setSerie(serie);
        serie.getSeasons().add(season);
        serieRepository.save(serie);
        serie.getSeasons().forEach(s -> s.setSerie(null));
        return serie;
    }

    @Override
    public Serie addImageUrl(int idSerie, String urlImage)
    {
        Serie serie = findById(idSerie);
        if(!Optional.ofNullable(serie.getImageSeries()).isPresent())
            serie.setImageSeries(new ArrayList<>());
        serie.getImageSeries().add(new ImageSerie(0, urlImage, serie));
        super.save(serie);
        return serie;
    }

    @Override
    public List<Serie> findByCategory(int category) {
        return serieRepository.findByCategory(category);
    }

    @Override
    public Serie findById(Integer id, String userName) {
        Serie serie = this.findById(id);
        if(!Optional.ofNullable(userName).isPresent()) setVideos(serie);
        else{
            Optional<Authorization> userO = authorizationRepository.findByEmail(userName);
            if(!userO.isPresent()) setVideos(serie);
            if(userO.isPresent() && !userO.get().isStatus()) setVideos(serie);
        }
        return serie;
    }

    @Override
    public Serie save(Serie entity) {
        Serie serie = super.save(entity);
        if(!Optional.ofNullable(serie.getSeasons()).isPresent())
            serie.setSeasons(new ArrayList<>());
        serie.getSeasons().add(new Season(0, serie.getName(), serie.getRemarks(), serie, new ArrayList<>()));
        super.save(serie);
        return serie;
    }
}
