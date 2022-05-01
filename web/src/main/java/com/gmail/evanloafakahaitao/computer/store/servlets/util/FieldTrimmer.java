package com.gmail.evanloafakahaitao.computer.store.servlets.util;

public class FieldTrimmer {

    public String trim(String field) {
        if (field != null && !field.equals("")) {
            return field.trim();
        }
        return null;
    }
}
