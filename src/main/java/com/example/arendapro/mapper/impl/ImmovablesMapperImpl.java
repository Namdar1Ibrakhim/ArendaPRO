package com.example.arendapro.mapper.impl;

import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.mapper.ImmovablesMapper;
import org.springframework.stereotype.Component;

@Component
public class ImmovablesMapperImpl implements ImmovablesMapper {


    @Override
    public ImmovableRequestDto toDto(Immovables immovables){
        ImmovableRequestDto immovablesDto = new ImmovableRequestDto();
        immovablesDto.setTitle(immovables.getTitle());
        immovablesDto.setNumOfRooms(immovables.getNumOfRooms());
        immovablesDto.setPrice(immovables.getPrice());
        immovablesDto.setDescription(immovables.getDescription());
        immovablesDto.setPropertyType(immovables.getPropertyType());
        immovablesDto.setState(immovables.getState());
        immovablesDto.setArea(immovables.getArea());
        immovablesDto.setAddress(immovables.getAddress().getCountry()+", "+immovables.getAddress().getRegion()+", "+immovables.getAddress().getDistrict()+", "+immovables.getAddress().getCity()+", "+immovables.getAddress().getStreet()+", "+immovables.getAddress().getImmovableNumber());

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

