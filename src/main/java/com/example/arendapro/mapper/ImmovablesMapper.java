package com.example.arendapro.mapper;

import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.entity.Immovables;


public interface ImmovablesMapper {

    ImmovableResponseDto toDto(Immovables immovables);

    Immovables toEntity(ImmovableRequestDto immovablesDto);

}
