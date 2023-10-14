package com.example.arendapro.mapper;

import com.example.arendapro.dto.ImmovablesDto;
import com.example.arendapro.entity.Immovables;

public interface ImmovablesMapper {

    ImmovablesDto toDto(Immovables immovables);

    Immovables toEntity(ImmovablesDto immovablesDto);

}
