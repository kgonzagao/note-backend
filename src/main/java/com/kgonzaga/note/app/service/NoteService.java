package com.kgonzaga.note.app.service;

import com.kgonzaga.note.app.presentation.dto.NoteCreateRequest;
import com.kgonzaga.note.app.presentation.dto.NoteResponse;
import com.kgonzaga.note.app.presentation.dto.NoteUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NoteService {

    NoteResponse createNote(NoteCreateRequest request);

    NoteResponse updateNote(NoteUpdateRequest request);

    NoteResponse getNoteById(Long id);

    Page<NoteResponse> getAllNotes(Pageable pageable);

    void deleteNoteById(Long id);
}
