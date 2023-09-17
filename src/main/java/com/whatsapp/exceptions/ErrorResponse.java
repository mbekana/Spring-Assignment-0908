package com.whatsapp.exceptions;

public class ErrorResponse {
    private final String timestamp;
    private final int status;
    private final String error;
    private final String message;

    public ErrorResponse(String timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    // Getter methods for timestamp, status, error, and message
}
