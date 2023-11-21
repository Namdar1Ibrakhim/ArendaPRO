package com.example.arendapro.mapper.impl;

import com.example.arendapro.dto.AddressRequestDto;
import com.example.arendapro.dto.AddressResponseDto;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.entity.address.*;
import com.example.arendapro.mapper.AddressMapper;
import com.example.arendapro.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddressMapperImpl implements AddressMapper {

    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final StreetRepository streetRepository;

    @Override
    public AddressResponseDto toDto(Address address) {
        AddressResponseDto addressResponseDto = new AddressResponseDto();
        addressResponseDto.setCountry(address.getCountry());
        addressResponseDto.setRegion(address.getRegion());
        addressResponseDto.setCity(address.getCity());
        addressResponseDto.setStreet(address.getStreet());
        addressResponseDto.setDistrict(address.getDistrict());
        addressResponseDto.setImmovableNumber(address.getImmovableNumber());
        return addressResponseDto;
    }

    @Override
    public Address toEntity(AddressRequestDto addressRequestDto) {
        if (addressRequestDto == null ) {
            return null;
        }
        Address.AddressBuilder address = Address.builder();

        address.country(countryRepository.findById(addressRequestDto.getCountry_id()).get());
        address.region(regionRepository.findById(addressRequestDto.getRegion_id()).get());
        address.city(cityRepository.findById(addressRequestDto.getCity_id()).get());
        address.district(districtRepository.findById(addressRequestDto.getDistrict_id()).get());
        address.street(streetRepository.findById(addressRequestDto.getStreet_id()).get());
        address.district(districtRepository.findById(addressRequestDto.getDistrict_id()).get());
        address.immovableNumber(addressRequestDto.getImmovableNumber());
        log.info(address.toString());

        return address.build();
    }

}
