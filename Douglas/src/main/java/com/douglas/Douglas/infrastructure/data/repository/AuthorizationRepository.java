package com.douglas.Douglas.infrastructure.data.repository;

import com.douglas.Douglas.core.model.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, Integer> {

    Optional<Authorization> findByEmail(String user);
}
