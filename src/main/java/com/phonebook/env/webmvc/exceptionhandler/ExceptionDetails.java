package com.phonebook.env.webmvc.exceptionhandler;

import java.time.LocalDateTime;


public record ExceptionDetails(LocalDateTime dateTime, String message, String details) {
}
