package com.douglas.Douglas.configuration.jwt;

import com.douglas.Douglas.infrastructure.dto.LoginRequest;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtProvider {

    @Value("${security.key}")
    private String secret;
    private final int expiration = (((1000 * 60) * 60) * 6);

    public String generateToken(Authentication authentication){
        UserDetails login = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(login.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration ))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getEmailFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean  validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException ex){
            log.error("MAL FORMED TOKEN: {}", ex.getMessage());
        }catch (UnsupportedJwtException ex){
            log.error("Un Supported TOKEN: {}", ex.getMessage());
        }catch (ExpiredJwtException ex){
            log.error("Expired TOKEN: {}", ex.getMessage());
        }catch (IllegalArgumentException ex){
            log.error("Illegal Argument TOKEN: {}", ex.getMessage());
        }catch (SignatureException ex){
            log.error("Signature TOKEN: {}", ex.getMessage());
        }
        return false;
    }
}
