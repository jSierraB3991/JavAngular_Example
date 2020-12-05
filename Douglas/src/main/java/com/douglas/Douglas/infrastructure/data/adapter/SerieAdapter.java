package com.douglas.Douglas.infrastructure.data.adapter;

import com.douglas.Douglas.core.model.ImageSerie;
import com.douglas.Douglas.core.model.Season;
import com.douglas.Douglas.core.model.Serie;
import com.douglas.Douglas.core.service.SerieService;
import com.douglas.Douglas.infrastructure.data.repository.SerieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SerieAdapter extends GenericAdapter<Serie> implements SerieService {

    public SerieAdapter(SerieRepository serieRepository){
        super(serieRepository);
    }

    @Override
    public Page<Serie> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Serie addSeason(int idSerie, Season season) {
        Serie serie = findById(idSerie);
        if(!Optional.ofNullable(serie.getSeasons()).isPresent())
            serie.setSeasons(new ArrayList<>());
        serie.getSeasons().add(season);
        update(serie);
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
    public Serie save(Serie entity) {
        Serie serie = super.save(entity);
        if(!Optional.ofNullable(serie.getSeasons()).isPresent())
            serie.setSeasons(new ArrayList<>());
        serie.getSeasons().add(new Season(0, serie.getName(), serie.getRemarks(), serie, new ArrayList<>()));
        super.save(serie);
        return serie;
    }
}
