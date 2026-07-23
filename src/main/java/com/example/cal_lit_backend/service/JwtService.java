package com.example.cal_lit_backend.service;

import com.example.cal_lit_backend.config.JwtConfig;
import com.example.cal_lit_backend.model.Role;
import com.example.cal_lit_backend.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {
  private final JwtConfig jwtConfig;
    public Jwt generateAccessToken(User user){
        final long tokenExpiration=86400;
        return generateToken(user, jwtConfig.getAccessTokenExpiration());
    }
    public Jwt generateRefreshToken(User user){
        final long tokenExpiration=604800;

return generateToken(user, jwtConfig.getRefreshTokenExpiration());
    }



    private Jwt generateToken(User user, long tokenExpiration) {
       var claims= Jwts.claims()
                .subject(user.getId())
                .add("email",user.getEmail()).
                add("name",user.getFirstName()).
               add("role",user.getRole()).
                issuedAt(new Date()).
                expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration)).
                build();


        return  new Jwt(claims, jwtConfig.getSecretKey());

    }

    public Jwt parseToken(String token){
        try {
            var claims= getClaims(token);
            return new Jwt(claims, jwtConfig.getSecretKey());
        }catch (JwtException ex){
            return null;
        }
    }



    private Claims getClaims(String token) {
        return Jwts.parser().
                verifyWith(jwtConfig.getSecretKey()).
                build().parseSignedClaims(token).getPayload();
    }




}
