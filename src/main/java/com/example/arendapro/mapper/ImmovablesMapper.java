package com.example.arendapro.mapper;

import com.example.arendapro.dto.AddressRequestDto;
import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.entity.address.Address;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
//import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImmovablesMapper{

    ImmovableResponseDto toDto(Immovables immovables);

    //@Mapping(target = "addressRequestDto", expression = "java(getAddressDto(immovablesRequestDto.getAddressRequestDto()))")
    Immovables toEntity(ImmovableRequestDto immovableRequestDto);

}
