package dev.patika.vetapp.v1.core.exception;

public class NotFoundCustomerException extends RuntimeException{
    public NotFoundCustomerException(String message) {
        super(message);
    }
}
