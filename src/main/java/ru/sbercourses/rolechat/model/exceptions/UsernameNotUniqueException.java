package ru.sbercourses.rolechat.model.exceptions;

public class UsernameNotUniqueException extends RuntimeException {
    public UsernameNotUniqueException(String string) {
        super(string);
    }
}
