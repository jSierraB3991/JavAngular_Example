package com.douglas.Douglas.configuration;

import com.douglas.Douglas.configuration.jwt.JwtProvider;
import com.douglas.Douglas.configuration.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class JwtTokenFilterConfiguration {

    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter(jwtProvider, userDetailsService);
    }
}
