package com.douglas.Douglas.configuration;

import com.douglas.Douglas.core.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthorizationService userService;
    private final String tokenSecurity;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfiguration(AuthorizationService userService,
                                    @Value("${security.key}") String tokenSecurity,
                                    BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.tokenSecurity = tokenSecurity;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new LoginFilter("/login", authenticationManager(), tokenSecurity),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(tokenSecurity),
                        UsernamePasswordAuthenticationFilter.class);
    }
}
