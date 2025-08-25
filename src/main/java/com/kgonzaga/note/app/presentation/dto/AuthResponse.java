package com.kgonzaga.note.app.presentation.dto;

public record AuthResponse(
        String type,
        String accessToken,
        String refreshToken
) {
}
