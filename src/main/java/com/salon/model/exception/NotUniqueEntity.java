package com.salon.model.exception;

public class NotUniqueEntity extends RuntimeException {
    public NotUniqueEntity(Throwable throwable) {
        super(throwable);
    }
}
