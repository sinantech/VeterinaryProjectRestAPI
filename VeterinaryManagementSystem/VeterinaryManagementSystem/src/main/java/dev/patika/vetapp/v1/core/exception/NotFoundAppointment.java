package dev.patika.vetapp.v1.core.exception;

public class NotFoundAppointment extends RuntimeException{
    public NotFoundAppointment(String message) {
        super(message);
    }
}
