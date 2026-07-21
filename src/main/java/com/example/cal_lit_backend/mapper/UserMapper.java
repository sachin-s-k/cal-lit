package com.example.cal_lit_backend.mapper;


import com.example.cal_lit_backend.dto.CreateUserRequest;
import com.example.cal_lit_backend.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(CreateUserRequest userRequest);
}
