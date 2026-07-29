package com.phonebook.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserConflictException extends RuntimeException {
    public UserConflictException(String message) {
        super(message);
    }
}
