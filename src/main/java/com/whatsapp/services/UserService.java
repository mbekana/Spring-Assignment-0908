package com.whatsapp.services;

import com.whatsapp.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean isUsernameTaken(String username);

    User registerUser(User user);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User getUserById(Long id);
}

