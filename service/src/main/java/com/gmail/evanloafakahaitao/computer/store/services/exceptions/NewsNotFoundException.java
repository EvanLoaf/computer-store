package com.gmail.evanloafakahaitao.computer.store.services.exceptions;

public class NewsNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6448937361998647646L;

    public NewsNotFoundException(String message) {
        super(message);
    }
}
