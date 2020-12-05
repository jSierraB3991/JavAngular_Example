package com.douglas.Douglas.infrastructure.data.adapter;

import com.douglas.Douglas.core.model.Authorization;
import com.douglas.Douglas.core.model.Role;
import com.douglas.Douglas.core.service.AuthorizationService;
import com.douglas.Douglas.infrastructure.data.repository.AuthorizationRepository;
import com.douglas.Douglas.infrastructure.data.repository.RoleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthorizationAdapter extends GenericAdapter<Authorization> implements AuthorizationService {

    private final AuthorizationRepository authorizationRepository;
    private final RoleRepository roleRepository;

    public AuthorizationAdapter(AuthorizationRepository authorizationRepository,
                                RoleRepository roleRepository) {
        super(authorizationRepository);
        this.authorizationRepository = authorizationRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Authorization save(Authorization entity) {
        Role role = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new RuntimeException("ROLE 'USER' NOT FOUND"));
        entity.setRole(role);
        return super.save(entity);
    }

    @Override
    public Authorization findByEmail(String email) {
        return authorizationRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email Error"));
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Authorization userO = findByEmail(username);
        return new User(userO.getEmail(), userO.getPassword(),
                true, true, true,
                true, Arrays.asList(getAuthority(userO.getRole())));
    }

    public GrantedAuthority getAuthority(Role authorizationRole) {
        return new SimpleGrantedAuthority(authorizationRole.getRoleName());
    }
}
