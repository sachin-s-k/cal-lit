package com.example.cal_lit_backend.controller;

import com.example.cal_lit_backend.config.JwtConfig;
import com.example.cal_lit_backend.dto.CreateUserRequest;
import com.example.cal_lit_backend.dto.JwtResponse;
import com.example.cal_lit_backend.dto.LoginUserRequest;
import com.example.cal_lit_backend.mapper.UserMapper;
import com.example.cal_lit_backend.repository.UserRepository;
import com.example.cal_lit_backend.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
private final UserMapper userMapper;
private final AuthenticationManager authenticationManager;
private final JwtService jwtService;
private final JwtConfig jwtConfig;
    private final UserRepository userRepository;

    @PostMapping("/register")
public ResponseEntity<Void> registerUser(@Valid @RequestBody CreateUserRequest createUserRequest){

    return ResponseEntity.ok().build();

}

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody
                                                 LoginUserRequest loginUserRequest,
                                             HttpServletResponse response){
authenticationManager.
        authenticate(
                new UsernamePasswordAuthenticationToken
                        (loginUserRequest.getEmail(),loginUserRequest.getPassword()));

var user= userRepository.findByEmail(loginUserRequest.getEmail()).orElseThrow();
var accessToken=jwtService.generateAccessToken(user);
var refreshToken=jwtService.generateRefreshToken(user);
var cookie= new Cookie("refreshTokne",refreshToken.toString());
cookie.setHttpOnly(true);
cookie.setPath("/auth/v1/refresh-token");
cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
cookie.setSecure(true);
response.addCookie(cookie);


        return ResponseEntity.ok(new JwtResponse(accessToken.toString()));

    }

    public ResponseEntity<JwtResponse>  refresh(
            @CookieValue(value="refreshToken") String refreshToken
    ){
       var jwt= jwtService.parseToken(refreshToken);
        if (jwt==null||jwt.isExpired()
        ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();


        }
        var userId= jwt.getUserId();

        var user=userRepository.findById(userId).orElseThrow();
        var accessToken= jwtService.generateAccessToken(user);
        return ResponseEntity.ok(new JwtResponse(accessToken.toString()));
    }



}
