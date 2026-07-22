package com.example.cal_lit_backend.service;

import com.example.cal_lit_backend.config.JwtConfig;
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
    public String generateAccessToken(User user){
        final long tokenExpiration=86400;
        return generateToken(user, jwtConfig.getAccessTokenExpiration());
    }
    public String generateRefreshToken(User user){
        final long tokenExpiration=604800;

return generateToken(user, jwtConfig.getRefreshTokenExpiration());
    }



    private String generateToken(User user, long tokenExpiration) {
        return Jwts
                .builder().
                subject(user.getId()).
                claim("email", user.getEmail()).
                claim("firstName", user.getFirstName()).
                issuedAt(new Date()).
                expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration)).
                signWith(jwtConfig.getSecretKey()).compact();
    }

    public boolean validateToken(String token){
     try{
         var claims = getClaims(token);
         return claims.getExpiration().after(new Date());
     }catch (JwtException ex){
         return false;
     }

    }

    private Claims getClaims(String token) {
        return Jwts.parser().
                verifyWith(jwtConfig.getSecretKey()).
                build().parseSignedClaims(token).getPayload();
    }
    public  String getIdFromToken(String token){
        return getClaims(token).getSubject();
    }

}
