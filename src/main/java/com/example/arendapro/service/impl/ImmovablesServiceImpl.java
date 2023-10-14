package com.example.arendapro.service.impl;

import com.example.arendapro.dto.AddressDto;
import com.example.arendapro.dto.ImmovablesDto;
import com.example.arendapro.repository.ImmovablesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImmovablesServiceImpl {

    private final ImmovablesRepository immovablesRepository;

    public void addImmovables(ImmovablesDto immovablesDto, AddressDto addressDto){

    }
}
