package com.douglas.Douglas.core.model;

import com.douglas.Douglas.core.enumeration.RolEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 100, nullable = false, unique = true)
    private RolEnum roleName;

    public Role(int id, RolEnum roleName){
        setId(id);
        this.roleName = roleName;
    }
}
