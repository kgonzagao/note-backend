package com.kgonzaga.note.app.presentation.dto;

import jakarta.validation.constraints.*;

import java.util.Set;

public record UserCreateRequest(
        @NotBlank
        @Size(max = 100)
        String fullName,

        @NotBlank
        @Size(max = 20)
        String dni,

        @NotBlank
        @Size(min = 5, max = 30)
        String username,

        @NotBlank
        @Size(min = 8, max = 100)
        String password,

        Set<Long> roleIds
) {
}
