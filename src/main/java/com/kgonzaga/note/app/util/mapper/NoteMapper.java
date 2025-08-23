package com.kgonzaga.note.app.util.mapper;

import com.kgonzaga.note.app.persistence.entity.Note;
import com.kgonzaga.note.app.presentation.dto.NoteCreateRequest;
import com.kgonzaga.note.app.presentation.dto.NoteResponse;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public Note fromCreateRequest(NoteCreateRequest request) {
        return Note.builder()
                .title(request.title())
                .content(request.content())
                .build();
    }

    public NoteResponse toResponse(Note note) {
        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }
}
