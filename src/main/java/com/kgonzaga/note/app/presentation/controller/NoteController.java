package com.kgonzaga.note.app.presentation.controller;

import com.kgonzaga.note.app.presentation.dto.NoteCreateRequest;
import com.kgonzaga.note.app.presentation.dto.NoteResponse;
import com.kgonzaga.note.app.presentation.dto.NoteUpdateRequest;
import com.kgonzaga.note.app.presentation.dto.PagedResponse;
import com.kgonzaga.note.app.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@Valid @RequestBody NoteCreateRequest request) {
        NoteResponse created = noteService.createNote(request);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping
    public ResponseEntity<NoteResponse> updateNote(@Valid @RequestBody NoteUpdateRequest request) {
        NoteResponse updated = noteService.updateNote(request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id) {
        NoteResponse response = noteService.getNoteById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PagedResponse<NoteResponse>> getAllNotes(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<NoteResponse> page = noteService.getAllNotes(pageable);

        PagedResponse<NoteResponse> response = new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }
}
