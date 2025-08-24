package com.kgonzaga.note.app.presentation.dto;

import jakarta.validation.constraints.*;

public record RoleCreateRequest(
        @NotBlank
        @Size(min = 4, max = 25)
        String name,

        @NotBlank
        @Size(min = 4, max = 500)
        String description
) {
}
