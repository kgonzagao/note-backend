package com.kgonzaga.note.app.util.mapper;

import com.kgonzaga.note.app.persistence.entity.UserApp;
import com.kgonzaga.note.app.presentation.dto.UserCreateRequest;
import com.kgonzaga.note.app.presentation.dto.UserResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserApp fromCreateRequest(UserCreateRequest request) {
        return UserApp.builder()
                .id(null)
                .fullName(request.fullName())
                .dni(request.dni())
                .username(request.username())
                .build();
    }

    public UserResponse toResponse(UserApp user) {
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getDni(),
                user.getUsername(),
                user.isEnabled(),
                user.getRoles().stream()
                        .map(roleApp -> roleApp.getName().toUpperCase())
                        .collect(Collectors.toSet()),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
