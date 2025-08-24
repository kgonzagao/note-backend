package com.kgonzaga.note.app.presentation.controller;


import com.kgonzaga.note.app.presentation.dto.PagedResponse;
import com.kgonzaga.note.app.presentation.dto.RoleCreateRequest;
import com.kgonzaga.note.app.presentation.dto.RoleResponse;
import com.kgonzaga.note.app.presentation.dto.RoleUpdateRequest;
import com.kgonzaga.note.app.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponse> createRol(@Valid @RequestBody RoleCreateRequest request) {
        RoleResponse created = roleService.createRol(request);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping
    public ResponseEntity<RoleResponse> updateRole(@Valid @RequestBody RoleUpdateRequest request) {
        RoleResponse updated = roleService.updateRol(request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id) {
        RoleResponse response = roleService.getRolById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PagedResponse<RoleResponse>> getAllNotes(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<RoleResponse> page = roleService.getAllRoles(pageable);

        PagedResponse<RoleResponse> response = new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Long id) {
        roleService.deleteRolById(id);
        return ResponseEntity.noContent().build();
    }
}
