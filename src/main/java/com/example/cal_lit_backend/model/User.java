package com.example.cal_lit_backend.model;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
@Getter
public class User extends  BaseDocument{
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String password;
    private String profileImageUrl;
    private String role;
}
