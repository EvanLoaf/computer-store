package com.gmail.evanloafakahaitao.computer.store.servlets.model;

public enum RequestMethodEnum {
    GET,
    POST;

    public static RequestMethodEnum getRequest(String request) {
        try {
            return RequestMethodEnum.valueOf(request.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.printf("Request %s not found%n", request.toUpperCase());
            e.printStackTrace();
        }
        return null;
    }
}
