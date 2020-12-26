package com.douglas.Douglas.infrastructure.data.adapter;

import com.douglas.Douglas.core.model.Season;
import com.douglas.Douglas.core.model.Serie;
import com.douglas.Douglas.core.model.VideoSeason;
import com.douglas.Douglas.core.service.SeasonService;
import com.douglas.Douglas.infrastructure.data.repository.SeasonRepository;
import com.douglas.Douglas.infrastructure.data.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeasonAdapter extends GenericAdapter<Season> implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final SerieRepository serieRepository;
    public SeasonAdapter(SeasonRepository seasonRepository,
                         SerieRepository serieRepository){
        super(seasonRepository);
        this. seasonRepository = seasonRepository;
        this.serieRepository = serieRepository;
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

    @Override
    public List<Season> findAllBySerie(Integer idSerie) {
        return seasonRepository.findAllBySerie(idSerie);
    }

    @Override
    public void deleteById(int id) {
        Optional<Season> seasonO = seasonRepository.findById(id);
        if(seasonO.isPresent()){
            Serie serie = serieRepository.findById(seasonO.get().getSerie().getId()).get();
            serie.setSeasons(serie.getSeasons().stream()
                    .filter(season -> season.getId()!=id)
                    .collect(Collectors.toList()));
            serieRepository.save(serie);
        }
        super.deleteById(id);
    }
}
