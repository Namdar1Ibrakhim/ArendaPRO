package com.example.arendapro.mapper.impl;

import com.example.arendapro.dto.AddressRequestDto;
import com.example.arendapro.dto.AddressResponseDto;
import com.example.arendapro.entity.address.*;
import com.example.arendapro.mapper.AddressMapper;
import com.example.arendapro.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        addressResponseDto.setImmovableNumber(address.getImmovableNumber());
        return addressResponseDto;
    }

    @Override
    public Address toEntity(AddressRequestDto addressRequestDto) {
        Address address = new Address();
        Country country = countryRepository.findById(addressRequestDto.getCountry_id()).get();
        Region region = regionRepository.findById(addressRequestDto.getRegion_id()).get();
        City city = cityRepository.findById(addressRequestDto.getCity_id()).get();
        District district = districtRepository.findById(addressRequestDto.getDistrict_id()).get();
        Street street = streetRepository.findById(addressRequestDto.getStreet_id()).get();
        address.setCountry(country);
        address.setRegion(region);
        address.setCity(city);
        address.setDistrict(district);
        address.setStreet(street);
        address.setImmovableNumber(addressRequestDto.getImmovableNumber());
        return address;
    }

}
