package com.douglas.Douglas.infrastructure.data.adapter;

import com.douglas.Douglas.configuration.jwt.JwtProvider;
import com.douglas.Douglas.core.model.Authorization;
import com.douglas.Douglas.core.model.Role;
import com.douglas.Douglas.core.service.AuthorizationService;
import com.douglas.Douglas.infrastructure.data.repository.AuthorizationRepository;
import com.douglas.Douglas.infrastructure.data.repository.RoleRepository;
import com.douglas.Douglas.infrastructure.exception.BusinessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorizationAdapter extends GenericAdapter<Authorization> implements AuthorizationService {

    private final AuthorizationRepository authorizationRepository;
    private final RoleRepository roleRepository;

    public AuthorizationAdapter(AuthorizationRepository authorizationRepository,
                                RoleRepository roleRepository) {
        super(authorizationRepository);
        this.authorizationRepository = authorizationRepository;
        this.roleRepository = roleRepository;
    }

    private Set<Role> setRole(Set<Role> roles){
        return roles.stream().map(rol -> {
            Optional<Role> role = roleRepository.findByRoleName(rol.getRoleName());
            if(!role.isPresent()) rol = roleRepository.save(rol);
            else rol = role.get();
            return rol;
        }).collect(Collectors.toSet());
    }

    @Override
    public Authorization save(Authorization entity) {
        entity.setRole(setRole(entity.getRole()));
        if(authorizationRepository.findByEmail(entity.getEmail()).isPresent())
            BusinessException.runException("save.user.duplicate.email");
        return super.save(entity);
    }

    @Override
    public Authorization findByEmail(String email) {
        return authorizationRepository.findByEmail(email)
                .orElseThrow(() -> BusinessException.runException("login.user.mail.error"));
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Authorization userO = findByEmail(username);
        return new User(userO.getEmail(), userO.getPassword(),
                true, true, true,
                true, userO.getAuthority());
    }
}
