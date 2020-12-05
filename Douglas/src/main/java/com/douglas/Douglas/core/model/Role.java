package com.douglas.Douglas.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity {

    @Column(name = "role_name", length = 100, nullable = false, unique = true)
    private String roleName;

    public Role(int id, String roleName){
        setId(id);
        setRoleName(roleName);
    }
}
