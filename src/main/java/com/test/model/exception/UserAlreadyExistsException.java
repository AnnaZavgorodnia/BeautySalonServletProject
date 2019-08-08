package com.test.model.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(Throwable throwable) {
        super(throwable);
    }
}
