package com.wynk.project.exceptions;

public class ServiceConnectionError extends RuntimeException {
    private String message;

    public ServiceConnectionError(String message)
    {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
