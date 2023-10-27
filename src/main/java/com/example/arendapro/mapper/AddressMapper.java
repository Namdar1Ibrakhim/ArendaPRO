package com.example.arendapro.mapper;

import com.example.arendapro.dto.AddressDto;
import com.example.arendapro.dto.UserDto;
import com.example.arendapro.entity.address.Address;
import com.example.arendapro.security.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto toDto(Address address);

    Address toEntity(AddressDto addressDto);
}
