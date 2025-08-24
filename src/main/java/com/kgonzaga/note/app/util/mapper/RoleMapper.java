package com.kgonzaga.note.app.util.mapper;

import com.kgonzaga.note.app.persistence.entity.RoleApp;
import com.kgonzaga.note.app.presentation.dto.RoleCreateRequest;
import com.kgonzaga.note.app.presentation.dto.RoleResponse;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleApp fromCreateRequest(RoleCreateRequest request) {
        return RoleApp.builder()
                .name(request.name().trim())
                .description(request.description().trim())
                .build();
    }

    public RoleResponse toResponse(RoleApp rol) {
        return new RoleResponse(
                rol.getId(),
                rol.getName().trim(),
                rol.getDescription().trim(),
                rol.getCreatedAt(),
                rol.getUpdatedAt()
        );
    }
}
