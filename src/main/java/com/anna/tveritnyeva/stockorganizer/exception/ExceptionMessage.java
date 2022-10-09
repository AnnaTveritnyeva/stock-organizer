package com.anna.tveritnyeva.stockorganizer.exception;

public enum ExceptionMessage {
    EXCEPTION_MESSAGE("EXCEPTION MESSAGE: "),
    ITEM_ALREADY_EXISTS("item already exists."),
    ITEM_DOES_NOT_EXIST("item doesn't exist.");

    private final String msg;

    ExceptionMessage(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}