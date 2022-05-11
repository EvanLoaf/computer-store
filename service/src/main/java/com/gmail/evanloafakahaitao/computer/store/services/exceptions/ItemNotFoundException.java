package com.gmail.evanloafakahaitao.computer.store.services.exceptions;

public class ItemNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6958772293078178458L;

    public ItemNotFoundException(String message) {
        super(message);
    }
}
