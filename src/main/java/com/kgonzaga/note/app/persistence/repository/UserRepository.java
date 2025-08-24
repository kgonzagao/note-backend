package com.kgonzaga.note.app.persistence.repository;

import com.kgonzaga.note.app.persistence.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {

    boolean existsByDniIgnoreCaseOrUsernameIgnoreCase(String dni, String username);

    Optional<UserDetails> findByUsernameIgnoreCase(String username);

    boolean existsByDniIgnoreCaseAndIdNot(String dni, Long id);
    
    boolean existsByUsernameIgnoreCaseAndIdNot(String username, Long id);
}
