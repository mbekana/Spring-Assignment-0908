package com.whatsapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ChatroomAlreadyExistsException extends RuntimeException {
    public ChatroomAlreadyExistsException(String message) {
        super(message);
    }
}
