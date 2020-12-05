package com.douglas.Douglas.core.service;

import com.douglas.Douglas.core.model.Authorization;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AuthorizationService extends UserDetailsService, GenericService<Authorization> {

    Authorization findByEmail(String email);
}
