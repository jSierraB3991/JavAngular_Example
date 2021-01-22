package com.douglas.Douglas.infrastructure.dto;

import com.douglas.Douglas.core.enumeration.RolEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Builder
public class RoleRest {

    @Enumerated(EnumType.STRING)
    public RolEnum rol;
}
