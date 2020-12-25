package com.douglas.Douglas.infrastructure.controller.publiccontroller;

import com.douglas.Douglas.core.application.SerieApplication;
import com.douglas.Douglas.infrastructure.dto.PageRest;
import com.douglas.Douglas.infrastructure.dto.SerieRest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/public/serie")
@RequiredArgsConstructor
public class SeriePublicController {

    private final SerieApplication serieApplication;

    @GetMapping
    public PageRest<SerieRest> findAll(Pageable pageable, @RequestHeader("user-name") String userName) {
        if(Optional.ofNullable(userName).orElse("").trim().equals("")) userName = null;
        PageRest<SerieRest> series =  serieApplication.findAll(pageable, userName);
        return series;
    }
}
