package com.whatsapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotInChatroomException extends RuntimeException {
    public UserNotInChatroomException(String message) {
        super(message);
    }
}
