package com.douglas.Douglas.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@Builder
public class JwtDto {
    private String token;
    private String bearer = "Bearer ";
    private String userName;
    private Collection<? extends GrantedAuthority> roles;
}
