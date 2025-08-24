package com.kgonzaga.note.app.service;

import com.kgonzaga.note.app.presentation.dto.RoleCreateRequest;
import com.kgonzaga.note.app.presentation.dto.RoleResponse;
import com.kgonzaga.note.app.presentation.dto.RoleUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

    RoleResponse createRol(RoleCreateRequest request);

    RoleResponse updateRol(RoleUpdateRequest request);

    RoleResponse getRolById(Long id);

    Page<RoleResponse> getAllRoles(Pageable pageable);

    void deleteRolById(Long id);
}
