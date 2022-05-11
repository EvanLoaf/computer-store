package com.gmail.evanloafakahaitao.computer.store.controllers.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class JspDateFormatter {

    private JspDateFormatter() {}

    public static String formatLocalDateTime(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
