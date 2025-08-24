package com.kgonzaga.note.app.presentation.dto;

import jakarta.validation.constraints.*;

public record NoteCreateRequest(
        @NotBlank
        @Size(min = 4, max = 50)
        String title,

        @NotBlank
        String content
) {
}
