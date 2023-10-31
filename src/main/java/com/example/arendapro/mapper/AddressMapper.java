package com.example.arendapro.mapper;

import com.example.arendapro.dto.AddressRequestDto;
import com.example.arendapro.dto.AddressResponseDto;
import com.example.arendapro.entity.address.Address;
import com.example.arendapro.service.AddressService;
//import org.mapstruct.Mapper;

//@Mapper
public interface AddressMapper {
    AddressResponseDto toDto(Address address);

    Address toEntity(AddressRequestDto addressDto);
}
