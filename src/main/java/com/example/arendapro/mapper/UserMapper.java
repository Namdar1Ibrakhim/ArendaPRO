package com.example.arendapro.mapper;

import com.example.arendapro.dto.UserDto;
import com.example.arendapro.security.user.User;

public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
