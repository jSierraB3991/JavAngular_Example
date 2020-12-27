package com.douglas.Douglas.infrastructure.data.repository;

import com.douglas.Douglas.core.model.VideoSeason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideosUrlRepository extends JpaRepository<VideoSeason, Integer> {
}
