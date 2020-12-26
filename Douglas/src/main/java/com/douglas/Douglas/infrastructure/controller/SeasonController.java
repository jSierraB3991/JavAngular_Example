package com.douglas.Douglas.infrastructure.controller;

import com.douglas.Douglas.core.application.SeasonApplication;
import com.douglas.Douglas.core.model.Season;
import com.douglas.Douglas.infrastructure.dto.SeasonRest;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/api/season")
public class SeasonController extends BaseController<SeasonRest, Season> {

    private final SeasonApplication seasonApplication;

    public SeasonController(SeasonApplication seasonApplication){
        super(seasonApplication);
        this.seasonApplication = seasonApplication;
    }

    @PostMapping("/add-url-video/{id-season}")
    public SeasonRest addUrlVideo(@PathVariable("id-season") int idSeason,
                                  @NotBlank(message = "Url of video is mandatory")
                                  @Size(max = 255, message = "The maximun character of url video is 255")
                                  @RequestBody String urlvideo)
    {
        return seasonApplication.addUrlVideo(idSeason, urlvideo);
    }


    @GetMapping("/by-serie/{id-serie}")
    public List<SeasonRest> findAllBySerie(@PathVariable("id-serie") Integer idSerie){
        return seasonApplication.findAllBySerie(idSerie);
    }
}
