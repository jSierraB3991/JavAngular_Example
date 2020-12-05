package com.douglas.Douglas.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authorization_role")
@Getter
@Setter
@NoArgsConstructor
public class AuthorizationRole extends BaseEntity {

    @ManyToOne(targetEntity = Authorization.class)
    private Authorization authorization;

    @ManyToOne(targetEntity = Role.class)
    private Role role;

    public AuthorizationRole(int id,
                             Authorization authorization,
                             Role role) {
        setId(id);
        setAuthorization(authorization);
        setRole(role);
    }
}
