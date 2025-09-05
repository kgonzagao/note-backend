package com.kgonzaga.note.app.auth.service.impl;

import com.kgonzaga.note.app.auth.jwt.JwtService;
import com.kgonzaga.note.app.auth.service.AuthService;
import com.kgonzaga.note.app.exception.InvalidCredentialsException;
import com.kgonzaga.note.app.presentation.dto.AuthResponse;
import com.kgonzaga.note.app.presentation.dto.LoginRequest;
import com.kgonzaga.note.app.presentation.dto.RefreshTokenRequest;
import com.kgonzaga.note.app.presentation.dto.UserCreateRequest;
import com.kgonzaga.note.app.service.UserService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    public AuthResponse register(UserCreateRequest request) {
        var userRegister = userService.createUser(request);
        var userLoad = userService.loadUserByUsername(userRegister.username());

        String accessToken = jwtService.generateAccessToken(userLoad);
        String refreshToken = jwtService.generateRefreshToken(userLoad);
        return new AuthResponse("Bearer", accessToken, refreshToken);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
        } catch (AuthenticationException ex) {
            throw new InvalidCredentialsException(ex.getMessage());
        }

        var userLoad = userService.loadUserByUsername(request.username());
        String accessToken = jwtService.generateAccessToken(userLoad);
        String refreshToken = jwtService.generateRefreshToken(userLoad);
        return new AuthResponse("Bearer", accessToken, refreshToken);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.refreshToken();

        if (!jwtService.validateToken(refreshToken)) {
            throw new JwtException("Invalid Refresh Token");
        }

        String username = jwtService.extractUsername(refreshToken);
        var user = userService.loadUserByUsername(username);
        String newAccessToken = jwtService.generateAccessToken(user);

        return new AuthResponse("Bearer", newAccessToken, refreshToken);
    }

    @Override
    public boolean checkTokenAdmin(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        var username = jwtService.extractUsername(token);
        var user = userService.loadUserByUsername(username);

        if (!jwtService.isTokenValid(token, user)) {
            return false;
        }
        return user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

    }
}
