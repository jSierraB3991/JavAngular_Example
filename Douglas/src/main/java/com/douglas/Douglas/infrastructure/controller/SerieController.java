package com.douglas.Douglas.infrastructure.controller;

import com.douglas.Douglas.core.application.SerieApplication;
import com.douglas.Douglas.core.model.Serie;
import com.douglas.Douglas.infrastructure.dto.PageRest;
import com.douglas.Douglas.infrastructure.dto.SeasonRest;
import com.douglas.Douglas.infrastructure.dto.SerieRest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/serie")
public class SerieController extends  BaseController<SerieRest, Serie> {

    private final SerieApplication serieApplication;

    public SerieController(SerieApplication serieApplication){
        super(serieApplication);
        this.serieApplication = serieApplication;
    }

    @PostMapping("/add-season/{id-serie}")
    public SerieRest addSeason(@PathVariable("id-serie") int idSerie, @RequestBody SeasonRest season) {
        return serieApplication.addSeason(idSerie, season);
    }

    @PostMapping("/add-image-url/{id-serie}")
    public SerieRest addImageUrl(@PathVariable("id-serie") int idSerie,
                     @RequestBody @Max(value = 255, message = "The maximum character of url image is 255")
                     @NotBlank(message = "The url of image is mandatory") String urlImage) {
        return serieApplication.addImageUrl(idSerie, urlImage.replace('"', ' ').trim());
    }

    @GetMapping("/pagination")
    public PageRest<SerieRest> findAll(Pageable pageable) {
        return serieApplication.findAll(pageable);
    }
}
