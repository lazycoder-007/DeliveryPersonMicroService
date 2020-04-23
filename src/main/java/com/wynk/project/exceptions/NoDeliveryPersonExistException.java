package com.wynk.project.exceptions;

public class NoDeliveryPersonExistException extends RuntimeException {

    String message;

    public NoDeliveryPersonExistException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
