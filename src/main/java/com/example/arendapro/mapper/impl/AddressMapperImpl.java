package com.example.arendapro.mapper.impl;

import com.example.arendapro.dto.AddressDto;
import com.example.arendapro.entity.address.Address;
import com.example.arendapro.mapper.AddressMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperImpl implements AddressMapper {
    @Override
    public AddressDto toDto(Address address) {
        return null;
    }

    @Override
    public Address toEntity(AddressDto addressDto) {
        return null;
    }
}
