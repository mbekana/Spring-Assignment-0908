package com.whatsapp.exceptions;

public class UserAlreadyInChatroomException extends RuntimeException {

    public UserAlreadyInChatroomException(String message) {
        super(message);
    }

    public UserAlreadyInChatroomException(String message, Throwable cause) {
        super(message, cause);
    }
}
