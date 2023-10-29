package com.example.arendapro.service;

import com.example.arendapro.dto.AddressRequestDto;
import com.example.arendapro.entity.address.*;

import java.util.List;

public interface AddressService {
    List<City> getAllCities();
    City getCityById(Integer id);
    List<Country> getAllCountries();
    Country getCountryById(Integer id);
    List<District> getAllDistricts();
    District getDistrictsById(Integer id);
    List<Region> getAllRegions();
    Region getRegionById(Integer id);
    List<Street> getAllStreets();
    Street getStreetById(Integer id);
    Address addAddress(AddressRequestDto addressRequestDto);
}
