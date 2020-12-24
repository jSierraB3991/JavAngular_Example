package com.douglas.Douglas.infrastructure.controller.publiccontroller;

import com.douglas.Douglas.configuration.jwt.JwtProvider;
import com.douglas.Douglas.core.application.AuthorizationApplication;
import com.douglas.Douglas.core.enumeration.RolEnum;
import com.douglas.Douglas.infrastructure.dto.AuthorizationRest;
import com.douglas.Douglas.infrastructure.dto.JwtDto;
import com.douglas.Douglas.infrastructure.dto.LoginRequest;
import com.douglas.Douglas.infrastructure.dto.RoleRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/public/authorization")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;
    private final AuthorizationApplication authorizationApplication;
    private final JwtProvider jwtProvider;

    @PostMapping("/save-user")
    public void saveUser(@RequestBody @Valid AuthorizationRest authorization){
        Set<RoleRest> roles = new HashSet<>();
        roles.add(RoleRest.builder().rol(RolEnum.ROLE_USER).build());
        authorization.setRoles(roles);
        authorizationApplication.save(authorization);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = JwtDto.builder()
                .token(jwt)
                .bearer("Bearer")
                .userName(userDetails.getUsername())
                .roles(userDetails.getAuthorities()).build();
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

}