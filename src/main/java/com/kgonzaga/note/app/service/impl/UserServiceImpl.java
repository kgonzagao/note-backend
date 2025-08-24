package com.kgonzaga.note.app.service.impl;

import com.kgonzaga.note.app.exception.DuplicateResourceException;
import com.kgonzaga.note.app.exception.ResourceNotFoundException;
import com.kgonzaga.note.app.persistence.entity.UserApp;
import com.kgonzaga.note.app.persistence.repository.RoleRepository;
import com.kgonzaga.note.app.persistence.repository.UserRepository;
import com.kgonzaga.note.app.presentation.dto.UserCreateRequest;
import com.kgonzaga.note.app.presentation.dto.UserResponse;
import com.kgonzaga.note.app.presentation.dto.UserUpdateRequest;
import com.kgonzaga.note.app.service.UserService;
import com.kgonzaga.note.app.util.mapper.UserMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.existsByDniIgnoreCaseOrUsernameIgnoreCase(
                request.dni().trim(),
                request.username().trim())) {
            throw new DuplicateResourceException(request.dni(), request.username());
        }
        log.info("Creating user dni: {}, username: {}", request.dni(), request.username());
        var roles = roleRepository.findAllById(request.roleIds());
        var user = mapper.fromCreateRequest(request);
        user.setPassword(request.password());//TODO bycryp
        user.setRoles(new HashSet<>(roles));
        UserApp saved = userRepository.save(user);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public UserResponse updateUser(UserUpdateRequest request) {
        UserApp user = userRepository.findById(request.id())
                .orElseThrow(() -> new ResourceNotFoundException(request.id()));

        boolean dniConflict = userRepository.existsByDniIgnoreCaseAndIdNot(request.dni().trim(), user.getId());
        boolean usernameConflict = userRepository.existsByUsernameIgnoreCaseAndIdNot(request.username().trim(), user.getId());

        if (dniConflict || usernameConflict) {
            throw new DuplicateResourceException(request.dni(), request.username());
        }

        log.info("Updating user ID: {}, dni: {}, username: {}",
                user.getId(), request.dni(), request.username());

        user.setFullName(request.fullName().trim());
        user.setDni(request.dni().trim());
        user.setUsername(request.username().trim());

        if (request.password() != null && !request.password().isBlank()) {
            user.setPassword(request.password()); // TODO: cifrar con BCrypt
        }

        if (request.enabled() != null) {
            user.setEnabled(request.enabled());
        }

        if (request.roleIds() != null && !request.roleIds().isEmpty()) {
            var roles = roleRepository.findAllById(request.roleIds());
            user.setRoles(new HashSet<>(roles));
        }

        UserApp updated = userRepository.save(user);
        return mapper.toResponse(updated);
    }


    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        log.info("Searching for User with ID: {}", id);
        return userRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> getAllUser(Pageable pageable) {
        log.info("Listing User with pagination: page {}, size {}", pageable.getPageNumber(), pageable.getPageSize());
        return userRepository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        log.info("Deleting User with ID: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameIgnoreCase(username)
                .map(userDetails -> {
                    log.info("Load User: {}", userDetails.getUsername());
                    return userDetails;
                })
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
