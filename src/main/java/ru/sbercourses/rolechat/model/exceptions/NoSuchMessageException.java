package ru.sbercourses.rolechat.model.exceptions;

public class NoSuchMessageException extends RuntimeException {
    public NoSuchMessageException(String message) {
        super(message);
    }
}
