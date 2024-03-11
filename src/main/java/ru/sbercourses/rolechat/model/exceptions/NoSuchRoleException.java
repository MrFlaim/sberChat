package ru.sbercourses.rolechat.model.exceptions;

public class NoSuchRoleException extends RuntimeException {
    public NoSuchRoleException(String message) {
        super(message);
    }
}
