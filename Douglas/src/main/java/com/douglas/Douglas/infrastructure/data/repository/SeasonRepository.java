package com.douglas.Douglas.infrastructure.data.repository;

import com.douglas.Douglas.core.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {

    @Query("SELECT season FROM Season season WHERE season.serie.id = :idSerie")
    List<Season> findAllBySerie(Integer idSerie);
}
