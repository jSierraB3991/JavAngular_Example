package com.douglas.Douglas.infrastructure.data.repository;

import com.douglas.Douglas.core.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Integer> {

    @Query("SELECT s FROM Serie s WHERE s.category.id = :category")
    List<Serie> findByCategory(int category);
}
