package com.kgonzaga.note.app.presentation.dto;

import jakarta.validation.constraints.*;

public record LoginRequest(
        @NotBlank
        @Size(min = 5, max = 30)
        String username,

        @NotBlank
        @Size(min = 8, max = 100)
        String password
) {
}
