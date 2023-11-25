package com.example.arendapro.mapper;

import com.example.arendapro.dto.UserDto;
import com.example.arendapro.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
