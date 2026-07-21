package com.example.cal_lit_backend.service;

import com.example.cal_lit_backend.dto.CreateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    public void  registerUser(CreateUserRequest userRequest){


    }

}
