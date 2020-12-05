package com.douglas.Douglas.infrastructure.data.adapter;

import com.douglas.Douglas.core.model.Authorization;
import com.douglas.Douglas.core.model.AuthorizationRole;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        Authorization auth = super.save(entity);
        List<Role> roles = roleRepository.findByRoleNames(getListAdminAuthority().stream()
                .map(ga -> ga.getAuthority())
                .collect(Collectors.toList()));
        entity.setAuthorizationRole(roles.stream().map(role->
                new AuthorizationRole(0, auth, role)).collect(Collectors.toList()));
        repository.save(auth);
        return auth;
    }

    @Override
    public Authorization findByEmail(String email) {
        return authorizationRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email Error"));
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException
    {
        Authorization userO = findByEmail(username);
        return new User(userO.getEmail(), userO.getPassword(),
                true, true, true, true, getListAdminAuthority());
    }

    public List<GrantedAuthority> getListAuthority()
    {
        return Arrays.asList(new SimpleGrantedAuthority(roleRepository.findByRoleName("USER")
                .orElseThrow(() ->new RuntimeException("Role Not Found")).getRoleName()));
    }

    public List<GrantedAuthority> getListAdminAuthority()
    {
        return roleRepository.findByRoleNames(Arrays.asList(new String[]{"USER", "ADMINS"})).stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}
