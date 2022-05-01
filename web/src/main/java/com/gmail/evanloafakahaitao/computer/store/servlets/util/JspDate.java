package com.gmail.evanloafakahaitao.computer.store.servlets.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class JspDate {

    private JspDate() {}

    public static String formatLocalDateTime(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
