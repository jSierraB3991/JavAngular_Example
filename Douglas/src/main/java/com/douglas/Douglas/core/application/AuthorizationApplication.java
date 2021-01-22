package com.douglas.Douglas.core.application;

import com.douglas.Douglas.core.model.Authorization;
import com.douglas.Douglas.core.model.Role;
import com.douglas.Douglas.core.service.AuthorizationService;
import com.douglas.Douglas.infrastructure.dto.AuthorizationRest;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorizationApplication extends GenericApplication<AuthorizationRest, Authorization> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper mapper;
    private final AuthorizationService authorizationService;

    public AuthorizationApplication(AuthorizationService service,
                                    BCryptPasswordEncoder bCryptPasswordEncoder,
                                    ModelMapper mapper) {
        super(service);
        authorizationService = service;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapper = mapper;
    }

    @Override
    public AuthorizationRest save(AuthorizationRest rest) {
        rest.setPassword(bCryptPasswordEncoder.encode(rest.getPassword()));
        return super.save(rest);
    }

    @Override
    protected AuthorizationRest convertToRest(Authorization dto) {
        return mapper.map(dto, AuthorizationRest.class);
    }

    @Override
    protected Authorization convertToDto(AuthorizationRest rest) {
        Authorization authorization = mapper.map(rest, Authorization.class);
        authorization.setRole(rest.getRoles().stream()
                .map(rol -> new Role(0, rol.getRol()))
                .collect(Collectors.toSet()));
        return authorization;
    }

}
