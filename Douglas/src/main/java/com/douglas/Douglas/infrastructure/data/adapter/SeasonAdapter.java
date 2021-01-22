package com.douglas.Douglas.infrastructure.data.adapter;

import com.douglas.Douglas.core.model.Season;
import com.douglas.Douglas.core.model.Serie;
import com.douglas.Douglas.core.model.VideoSeason;
import com.douglas.Douglas.core.service.SeasonService;
import com.douglas.Douglas.infrastructure.data.repository.SeasonRepository;
import com.douglas.Douglas.infrastructure.data.repository.SerieRepository;
import com.douglas.Douglas.infrastructure.data.repository.VideosUrlRepository;
import com.douglas.Douglas.infrastructure.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeasonAdapter extends GenericAdapter<Season> implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final SerieRepository serieRepository;
    private final VideosUrlRepository videosUrlRepository;
    public SeasonAdapter(SeasonRepository seasonRepository,
                         SerieRepository serieRepository,
                         VideosUrlRepository videosUrlRepository){
        super(seasonRepository);
        this.videosUrlRepository = videosUrlRepository;
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
    public void deleteChapter(Integer idSeason, Integer idChapter) {
        Season season = seasonRepository.findById(idSeason).orElseThrow(() -> BusinessException.runException("Temporada incorrecta"));
        if(season.getVideoSeasons().stream().map(vd -> vd.getId()).filter(ids -> ids.equals(idChapter)).collect(Collectors.toList()).isEmpty())
            throw BusinessException.runException("Este capitulo no esta en esta temporada");
        season.setVideoSeasons(season.getVideoSeasons().stream().filter(vd -> vd.getId()!=idChapter).collect(Collectors.toList()));
        seasonRepository.save(season);
        videosUrlRepository.deleteById(idChapter);
    }

    @Override
    public List<Season> findAllBySerie(Integer idSerie) {
        return seasonRepository.findAllBySerie(idSerie);
    }

    @Override
    public List<VideoSeason> findChaptersBySeason(Integer idSeason) {
        return seasonRepository.findById(idSeason)
                .orElseThrow(() -> BusinessException.runException("Temporada no encontrada: " + idSeason))
                .getVideoSeasons();
    }

    @Override
    public void deleteById(int id) {
        Optional<Season> seasonO = seasonRepository.findById(id);
        if(seasonO.isPresent()){
            if(!seasonO.get().getVideoSeasons().isEmpty())
                BusinessException.runException("Esta temporada tiene uno o más capitulos eliminelos para continuar");
            Serie serie = serieRepository.findById(seasonO.get().getSerie().getId()).get();
            serie.setSeasons(serie.getSeasons().stream()
                    .filter(season -> season.getId()!=id)
                    .collect(Collectors.toList()));
            serieRepository.save(serie);
        }
        super.deleteById(id);
    }
}
