package com.kgonzaga.note.app.auth.service;

import com.kgonzaga.note.app.presentation.dto.AuthResponse;
import com.kgonzaga.note.app.presentation.dto.LoginRequest;
import com.kgonzaga.note.app.presentation.dto.RefreshTokenRequest;
import com.kgonzaga.note.app.presentation.dto.UserCreateRequest;

public interface AuthService {

    AuthResponse register(UserCreateRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(RefreshTokenRequest request);

    boolean checkTokenAdmin(String token);
}
