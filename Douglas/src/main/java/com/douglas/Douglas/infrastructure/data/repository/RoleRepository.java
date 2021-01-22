package com.douglas.Douglas.infrastructure.data.repository;

import com.douglas.Douglas.core.enumeration.RolEnum;
import com.douglas.Douglas.core.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("SELECT role FROM Role role WHERE role.roleName = :roleName")
    Optional<Role> findByRoleName(RolEnum roleName);


    @Query("SELECT role FROM Role role WHERE role.roleName IN :roleNames")
    List<Role> findByRoleNames(List<String> roleNames);
}
