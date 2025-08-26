package com.kgonzaga.note.app.util;

import com.kgonzaga.note.app.persistence.entity.UserApp;
import com.kgonzaga.note.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUtil {

    private final UserService userService;

    public UserApp getUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return (UserApp) userService.loadUserByUsername(username);
    }


}
