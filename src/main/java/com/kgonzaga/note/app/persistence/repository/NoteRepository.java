package com.kgonzaga.note.app.persistence.repository;

import com.kgonzaga.note.app.persistence.entity.Note;
import com.kgonzaga.note.app.persistence.entity.UserApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    boolean existsByTitleIgnoreCase(String title);

    boolean existsByTitleIgnoreCaseAndIdNot(String title, Long id);

    Page<Note> findAllByUserApp(Pageable pageable, UserApp userApp);
}
