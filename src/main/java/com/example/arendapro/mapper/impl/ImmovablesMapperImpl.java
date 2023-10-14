package com.example.arendapro.mapper.impl;

import com.example.arendapro.dto.ImmovablesDto;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.mapper.ImmovablesMapper;
import org.springframework.stereotype.Component;

@Component
public class ImmovablesMapperImpl implements ImmovablesMapper {


    @Override
    public ImmovablesDto toDto(Immovables immovables) {
        return null;
    }

    @Override
    public Immovables toEntity(ImmovablesDto immovablesDto) {
        Immovables immovables = new Immovables();
        immovables.setTitle(immovablesDto.getTitle());
        immovables.setNumOfRooms(immovablesDto.getNumOfRooms());
        immovables.setArea(immovablesDto.getArea());
        return immovables;
    }
}

