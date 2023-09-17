package com.whatsapp.controllers;

import com.whatsapp.dtos.LoginRequestDTO;
import com.whatsapp.dtos.RegistrationResponse;
import com.whatsapp.dtos.UserDTO;
import com.whatsapp.entity.User;
import com.whatsapp.exceptions.CustomErrorResponse;
import com.whatsapp.mappers.UserMapper;
import com.whatsapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        if (userService.isUsernameTaken(userDTO.getUsername())) {
            CustomErrorResponse errorResponse = new CustomErrorResponse("Username is already taken.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        User registeredUser = userService.registerUser(userMapper.toUser(userDTO));

        if (registeredUser != null) {
            RegistrationResponse response = new RegistrationResponse("Registration successful!", registeredUser);
            return ResponseEntity.ok(response);
        } else {
            CustomErrorResponse errorResponse = new CustomErrorResponse("Failed to register user.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO authRequest) {
        try {
            UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
            return ResponseEntity.ok(userDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
