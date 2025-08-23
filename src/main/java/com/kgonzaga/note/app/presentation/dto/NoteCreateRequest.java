package com.kgonzaga.note.app.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record NoteCreateRequest(
        @NotBlank
        @Size(min = 4, max = 50)
        String title,

        @NotBlank
        String content
) {
}
