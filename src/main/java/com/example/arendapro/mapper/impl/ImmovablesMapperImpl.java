package com.example.arendapro.mapper.impl;

import com.example.arendapro.dto.ImmovablesDto;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.mapper.ImmovablesMapper;
import org.springframework.stereotype.Component;

@Component
public class ImmovablesMapperImpl implements ImmovablesMapper {


    @Override
    public ImmovablesDto toDto(Immovables immovables){
        ImmovablesDto immovablesDto = new ImmovablesDto();
        immovablesDto.setTitle(immovables.getTitle());
        immovablesDto.setNumOfRooms(immovables.getNumOfRooms());
        immovablesDto.setPrice(immovables.getPrice());
        immovablesDto.setDescription(immovables.getDescription());
        immovablesDto.setPropertyType(immovables.getPropertyType());
        immovablesDto.setState(immovables.getState());
        immovablesDto.setArea(immovables.getArea());

        return immovablesDto;
    }

    @Override
    public Immovables toEntity(ImmovablesDto immovablesDto) {
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

