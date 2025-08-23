package com.kgonzaga.note.app.exception;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(Long id) {
        super("Note with ID " + id + " not found.");
    }
}