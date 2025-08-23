package com.kgonzaga.note.app.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NoteUpdateRequest(
        @NotNull
        Long id,

        @NotBlank
        @Size(min = 4, max = 50)
        String title,

        @NotBlank
        String content
) {
}
