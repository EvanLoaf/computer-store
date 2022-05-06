package com.gmail.evanloafakahaitao.store.dao.model;

public enum RoleEnum {

    USER,
    ADMIN;

    public static RoleEnum getRole(String role) {
        try {
            return RoleEnum.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.printf("Role %s not found%n", role);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
