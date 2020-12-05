package com.douglas.Douglas.infrastructure.controller.publiccontroller;

import com.douglas.Douglas.core.application.AuthorizationApplication;
import com.douglas.Douglas.infrastructure.dto.AuthorizationRest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/public/authorization")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationApplication authorizationApplication;

    @PostMapping("/save-user")
    public void saveUser(@RequestBody @Valid AuthorizationRest authorization){
        authorizationApplication.save(authorization);
    }

}