package com.kgonzaga.note.app.service.impl;

import com.kgonzaga.note.app.exception.ResourceNotFoundException;
import com.kgonzaga.note.app.persistence.entity.RoleApp;
import com.kgonzaga.note.app.persistence.repository.RoleRepository;
import com.kgonzaga.note.app.presentation.dto.RoleCreateRequest;
import com.kgonzaga.note.app.presentation.dto.RoleResponse;
import com.kgonzaga.note.app.presentation.dto.RoleUpdateRequest;
import com.kgonzaga.note.app.service.RoleService;
import com.kgonzaga.note.app.util.mapper.RoleMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public RoleResponse createRol(RoleCreateRequest request) {
        if (repository.existsByNameIgnoreCase(request.name().trim())) {
            throw new DataIntegrityViolationException("Exception create unique");
        }
        log.info("Creating new rol: {}", request.name());
        RoleApp saved = repository.save(mapper.fromCreateRequest(request));
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public RoleResponse updateRol(RoleUpdateRequest request) {
        RoleApp existing = repository.findById(request.id())
                .orElseThrow(() -> new ResourceNotFoundException(request.id()));

        if (repository.existsByNameIgnoreCaseAndIdNot(request.name().trim(), request.id())) {
            throw new DataIntegrityViolationException("Exception update unique");
        }

        log.info("Updating rol with ID: {}", request.id());
        existing.setName(request.name());
        existing.setDescription(request.description());

        RoleApp saved = repository.save(existing);
        entityManager.flush();
        entityManager.refresh(saved);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponse getRolById(Long id) {
        log.info("Searching for role with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RoleResponse> getAllRoles(Pageable pageable) {
        log.info("Listing rol with pagination: page {}, size {}", pageable.getPageNumber(), pageable.getPageSize());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public void deleteRolById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        log.info("Deleting rol with ID: {}", id);
        repository.deleteById(id);
    }
}
