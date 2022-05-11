package com.gmail.evanloafakahaitao.computer.store.services.exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3327566476378927900L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
