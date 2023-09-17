package com.whatsapp.dtos;

import com.whatsapp.entity.User;
import lombok.Data;

@Data
public class RegistrationResponse {
    private String message;
    private User user;

    public RegistrationResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }

}
