package com.kgonzaga.note.app.auth.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateAccessToken(UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);

    boolean validateToken(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String extractUsername(String token);
}
