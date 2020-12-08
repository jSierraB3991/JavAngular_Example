package com.douglas.Douglas.infrastructure.controller.publiccontroller;

import com.douglas.Douglas.core.application.SerieApplication;
import com.douglas.Douglas.infrastructure.dto.PageRest;
import com.douglas.Douglas.infrastructure.dto.SerieRest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/serie")
@RequiredArgsConstructor
public class SeriePublicController {

    private final SerieApplication serieApplication;

    @GetMapping
    public PageRest<SerieRest> findAll(Pageable pageable) {
        return serieApplication.findAll(pageable);
    }
}
