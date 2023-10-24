package com.example.arendapro.service;

import com.example.arendapro.dto.AddressDto;
import com.example.arendapro.dto.ImmovablesDto;
import com.example.arendapro.entity.Immovables;

import java.util.List;

public interface ImmovablesService {
    ImmovablesDto addImmovable(ImmovablesDto immovablesDto, AddressDto addressDto);
    void deleteImmovable(Integer immovables_id) throws Exception;
    ImmovablesDto editImmovable(ImmovablesDto immovablesDto, AddressDto addressDto);
    List<ImmovablesDto> getAllImmovables();
    ImmovablesDto findImmovable(Integer immovables_id);
    List<ImmovablesDto> findMyImmovables();
    List<ImmovablesDto> findImmovablesByOwner(Integer owner_id);
}
