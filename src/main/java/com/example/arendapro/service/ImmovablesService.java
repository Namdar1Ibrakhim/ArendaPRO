package com.example.arendapro.service;

import com.example.arendapro.dto.AddressDto;
import com.example.arendapro.dto.ImmovablesDto;

public interface ImmovablesService {
    ImmovablesDto addImmovables(ImmovablesDto immovablesDto, AddressDto addressDto);
}
