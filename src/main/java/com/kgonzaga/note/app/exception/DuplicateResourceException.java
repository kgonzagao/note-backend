package com.kgonzaga.note.app.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String dni, String username) {
        super("Dni already exists " + dni + " or Username already exists " + username);
    }
}
