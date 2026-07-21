package com.example.cal_lit_backend.controller;

import com.example.cal_lit_backend.dto.CreateUserRequest;
import com.example.cal_lit_backend.mapper.UserMapper;
import com.example.cal_lit_backend.model.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
private final UserMapper userMapper;
private final PasswordEncoder passwordEncoder;
@PostMapping("/register")
public ResponseEntity<Void> registerUser(@Valid @RequestBody CreateUserRequest createUserRequest){

    return ResponseEntity.ok().build();

}


}
