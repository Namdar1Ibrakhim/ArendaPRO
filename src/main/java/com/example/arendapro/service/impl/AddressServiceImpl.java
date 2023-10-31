package com.example.arendapro.service.impl;

import com.example.arendapro.dto.AddressRequestDto;
import com.example.arendapro.entity.address.*;
import com.example.arendapro.repository.*;
import com.example.arendapro.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;
    private final StreetRepository streetRepository;


    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityById(Integer id) {
        return cityRepository.findById(id).get();
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Integer id) {
        return countryRepository.findById(id).get();
    }

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public District getDistrictsById(Integer id) {
        return districtRepository.findById(id).get();
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region getRegionById(Integer id) {
        return regionRepository.findById(id).get();
    }

    @Override
    public List<Street> getAllStreets() {
        return streetRepository.findAll();
    }

    @Override
    public Street getStreetById(Integer id) {
        return streetRepository.findById(id).get();
    }

    @Override
    public void addAddress(Address address) {
        addressRepository.save(address);
    }



}
