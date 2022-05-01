package com.gmail.evanloafakahaitao.computer.store.servlets.model;

public enum CommandEnum {
    LOGIN("/dispatcher?command=login"),
    USERS("/dispatcher?command=users"),
    ITEMS("/dispatcher?command=items"),
    LOAD_ITEMS("/dispatcher?command=load_items"),
    UPDATE_USER_MENU("/dispatcher?command=update_user_menu"),
    UPDATE_USER("/dispatcher?command=update_user"),
    MAKE_ORDER("/dispatcher?command=make_order"),
    SUBMIT_ORDER("/dispatcher?command=submit_order"),
    ORDERS("/dispatcher?command=orders"),
    DELETE_ORDER("/dispatcher?command=delete_order");

    private final String url;

    CommandEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public static CommandEnum getCommand(String command) {
        try {
            return CommandEnum.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.printf("Command %s not found%n", command.toUpperCase());
            e.printStackTrace();
        }
        return null;
    }
}
