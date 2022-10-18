package com.anna.tveritnyeva.stockorganizer.exception;

public class CategoryException extends Exception {
    public CategoryException(ExceptionMessage message) {
        super(ExceptionMessage.EXCEPTION_MESSAGE.getMsg() + message.getMsg());
    }
}
