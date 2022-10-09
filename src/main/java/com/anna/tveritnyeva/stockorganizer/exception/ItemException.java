package com.anna.tveritnyeva.stockorganizer.exception;

public class ItemException extends Exception {

    public ItemException(ExceptionMessage message) {
        super(ExceptionMessage.EXCEPTION_MESSAGE.getMsg() + message.getMsg());
    }
}