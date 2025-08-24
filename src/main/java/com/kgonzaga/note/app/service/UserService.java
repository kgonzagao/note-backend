package com.kgonzaga.note.app.service;

import com.kgonzaga.note.app.presentation.dto.UserCreateRequest;
import com.kgonzaga.note.app.presentation.dto.UserResponse;
import com.kgonzaga.note.app.presentation.dto.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserResponse createUser(UserCreateRequest request);

    UserResponse updateUser(UserUpdateRequest request);

    UserResponse getUserById(Long id);

    Page<UserResponse> getAllUser(Pageable pageable);

    void deleteUserById(Long id);
}
