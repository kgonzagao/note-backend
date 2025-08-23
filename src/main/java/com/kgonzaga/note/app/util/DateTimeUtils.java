package com.kgonzaga.note.app.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeUtils {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String format(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

    public String now() {
        return format(LocalDateTime.now());
    }
}
