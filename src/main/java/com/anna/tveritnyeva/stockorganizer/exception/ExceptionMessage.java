package com.anna.tveritnyeva.stockorganizer.exception;

public enum ExceptionMessage {
    EXCEPTION_MESSAGE("EXCEPTION MESSAGE: "),

    //Item exceptions:
    ITEM_ALREADY_EXISTS("item already exists."),
    ITEM_DOES_NOT_EXIST("item doesn't exist."),

    //Category exceptions:
    CATEGORY_NAME_ALREADY_EXIST("category name already exist"),
    CATEGORY_DOES_NOT_EXIST("category doesn't exist"),
    SECTION_ALREADY_EXISTS("section already exists");

    private final String msg;

    ExceptionMessage(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}