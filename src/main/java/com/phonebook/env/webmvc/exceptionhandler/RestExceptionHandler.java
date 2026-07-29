package com.phonebook.env.webmvc.exceptionhandler;

import com.phonebook.core.exception.UserConflictException;
import com.phonebook.core.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleDefault(Exception exception, WebRequest request) {
        ExceptionDetails details = new ExceptionDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleNotFound(Exception exception, WebRequest request) {
        ExceptionDetails details = new ExceptionDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserConflictException.class)
    public ResponseEntity<ExceptionDetails> handleUserExists(Exception exception, WebRequest request) {
        ExceptionDetails details = new ExceptionDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode code,
            WebRequest request
    ) {
        ExceptionDetails details = new ExceptionDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
