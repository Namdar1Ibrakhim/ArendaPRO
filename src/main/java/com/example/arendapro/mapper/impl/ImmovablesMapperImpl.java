package com.example.arendapro.mapper.impl;

import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.mapper.AddressMapper;
import com.example.arendapro.mapper.ImmovablesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImmovablesMapperImpl implements ImmovablesMapper {

    private final AddressMapper addressMapper;

    @Override
    public ImmovableResponseDto toDto(Immovables immovables){
        ImmovableResponseDto immovablesDto = new ImmovableResponseDto();
        immovablesDto.setTitle(immovables.getTitle());
        immovablesDto.setNumOfRooms(immovables.getNumOfRooms());
        immovablesDto.setPrice(immovables.getPrice());
        immovablesDto.setDescription(immovables.getDescription());
        immovablesDto.setPropertyType(immovables.getPropertyType());
        immovablesDto.setState(immovables.getState());
        immovablesDto.setArea(immovables.getArea());
        immovablesDto.setAddressResponseDto(addressMapper.toDto(immovables.getAddress()));

        return immovablesDto;
    }

    @Override
    public Immovables toEntity(ImmovableRequestDto immovablesDto) {
        Immovables immovables = new Immovables();
        immovables.setTitle(immovablesDto.getTitle());
        immovables.setNumOfRooms(immovablesDto.getNumOfRooms());
        immovables.setArea(immovablesDto.getArea());
        immovables.setDescription(immovablesDto.getDescription());
        immovables.setPrice(immovablesDto.getPrice());
        immovables.setPropertyType(immovablesDto.getPropertyType());
        immovables.setState(immovablesDto.getState());
        return immovables;
    }
}

