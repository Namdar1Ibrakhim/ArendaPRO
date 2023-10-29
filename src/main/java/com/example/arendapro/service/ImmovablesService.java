package com.example.arendapro.service;

import com.example.arendapro.dto.AddressDto;
import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;

import java.util.List;

public interface ImmovablesService {
    ImmovableResponseDto addImmovable(ImmovableRequestDto immovablesDto);
    void deleteImmovable(Integer immovables_id) throws Exception;
    ImmovableResponseDto editImmovable(ImmovableRequestDto immovablesDto);
    List<ImmovableResponseDto> getAllImmovables();
    ImmovableResponseDto findImmovable(Integer immovables_id);
    List<ImmovableResponseDto> findMyImmovables();
    List<ImmovableResponseDto> findImmovablesByOwner(Integer owner_id);
}
