package com.LABMedical.service;

import com.LABMedical.exception.TokenValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public String generateToken(UserDetails userDetails) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(36000))
                .subject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities())
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String extractUsername(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            return jwt.getSubject();
        } catch (JwtException e) {
            throw new TokenValidationException("Token inválido: " + e.getMessage());
        }
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            return jwt.getExpiresAt().isBefore(Instant.now());
        } catch (JwtException e) {
            throw new TokenValidationException("Token inválido: " + e.getMessage());
        }
    }

    public org.springframework.security.authentication.UsernamePasswordAuthenticationToken getAuthentication(String token, UserDetails userDetails) {
        var authorities = userDetails.getAuthorities();
        return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }
}