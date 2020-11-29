package com.douglas.Douglas.infrastructure.controller;

import com.douglas.Douglas.core.application.SeasonApplication;
import com.douglas.Douglas.infrastructure.dto.SeasonRest;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/season")
public class SeasonController {

    private final SeasonApplication seasonApplication;

    public SeasonController(SeasonApplication seasonApplication){
        this.seasonApplication = seasonApplication;
    }

    @PostMapping("/{id-season}")
    public SeasonRest addUrlVideo(@PathVariable("id-season") int idSeason,
                                  @NotBlank(message = "Url of video is mandatory")
                                  @Max(value = 255, message = "The maximun character of url video is 255")
                                  @RequestBody String urlvideo)
    {
        return seasonApplication.addUrlVideo(idSeason, urlvideo);
    }
}
