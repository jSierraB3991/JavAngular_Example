package com.douglas.Douglas.configuration.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = getToken(request);
            if(Optional.ofNullable(token).isPresent() && jwtProvider.validateToken(token)){
                String email = jwtProvider.getEmailFromToken(token);
                UserDetails authorization = userDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        authorization,null,authorization.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        catch (Exception ex){
            log.error("Failed Method doFilterInternal {}", ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest req){
        String header = req.getHeader("Authorization");
        if(Optional.ofNullable(header).isPresent() && header.startsWith("Bearer"))
            return header.replace("Bearer ", "");
        return null;
    }
}
