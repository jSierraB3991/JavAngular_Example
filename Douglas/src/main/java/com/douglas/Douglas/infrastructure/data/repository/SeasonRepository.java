package com.douglas.Douglas.infrastructure.data.repository;

import com.douglas.Douglas.core.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Integer> {
}