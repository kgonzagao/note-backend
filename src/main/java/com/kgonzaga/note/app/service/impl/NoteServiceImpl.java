package com.kgonzaga.note.app.service.impl;

import com.kgonzaga.note.app.exception.ResourceNotFoundException;
import com.kgonzaga.note.app.persistence.entity.Note;
import com.kgonzaga.note.app.persistence.entity.UserApp;
import com.kgonzaga.note.app.persistence.repository.NoteRepository;
import com.kgonzaga.note.app.presentation.dto.NoteCreateRequest;
import com.kgonzaga.note.app.presentation.dto.NoteResponse;
import com.kgonzaga.note.app.presentation.dto.NoteUpdateRequest;
import com.kgonzaga.note.app.service.NoteService;
import com.kgonzaga.note.app.service.UserService;
import com.kgonzaga.note.app.util.mapper.NoteMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;
    private final UserService userService;
    private final NoteMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public NoteResponse createNote(NoteCreateRequest request) {
        if (repository.existsByTitleIgnoreCase(request.title().trim())) {
            throw new DataIntegrityViolationException("Exception create unique");
        }
        log.info("Creating new note: {}", request.title());
        var note = mapper.fromCreateRequest(request);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        note.setUserApp((UserApp) userService.loadUserByUsername(auth.getName()));
        Note saved = repository.save(note);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public NoteResponse updateNote(NoteUpdateRequest request) {
        Note existing = repository.findById(request.id())
                .orElseThrow(() -> new ResourceNotFoundException(request.id()));

        if (repository.existsByTitleIgnoreCaseAndIdNot(request.title().trim(), request.id())) {
            throw new DataIntegrityViolationException("Exception update unique");
        }

        log.info("Updating note with ID: {}", request.id());
        existing.setTitle(request.title());
        existing.setContent(request.content());

        Note saved = repository.save(existing);
        entityManager.flush();
        entityManager.refresh(saved);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public NoteResponse getNoteById(Long id) {
        log.info("Searching for note with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoteResponse> getAllNotes(Pageable pageable) {
        log.info("Listing notes with pagination: page {}, size {}", pageable.getPageNumber(), pageable.getPageSize());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public void deleteNoteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        log.info("Deleting note with ID: {}", id);
        repository.deleteById(id);
    }
}
