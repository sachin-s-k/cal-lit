package com.example.cal_lit_backend.service;

import com.example.cal_lit_backend.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public class Jwt {
    private final Claims claims;
    private final SecretKey secretKey;

    public Jwt(Claims claims, SecretKey secretKey) {
        this.claims = claims;
        this.secretKey = secretKey;
    }
    public boolean isExpired(){
       return  claims.getExpiration().before(new Date());
    }
    public String getUserId(){
        return claims.getSubject();
    }
   public Role getRoleFromToken(String token){
       return Role.valueOf(claims.get("role",String.class));

   }
   public String toString(){
return Jwts.builder().claims(claims).signWith(secretKey).compact();
   }
}
