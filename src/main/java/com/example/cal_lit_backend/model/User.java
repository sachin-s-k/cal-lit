package com.example.cal_lit_backend.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
public class User extends  BaseDocument{
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String password;
    private String profileImageUrl;
}
