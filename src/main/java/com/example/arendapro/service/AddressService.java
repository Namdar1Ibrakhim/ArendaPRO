package com.example.arendapro.service;

import com.example.arendapro.dto.AddressRequestDto;
import com.example.arendapro.entity.address.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AddressService {

    List<City> getAllCities();
    City getCityById(Integer id);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void addCity(String cityName);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void deleteCity(Integer city_id);

    List<Country> getAllCountries();
    Country getCountryById(Integer id);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void addCountry(String iso, String countryName);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void deleteCountry(Integer country_id);

    List<District> getAllDistricts();
    District getDistrictsById(Integer id);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void addDistrict(String districtName);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void deleteDistrict(Integer district_id);

    List<Region> getAllRegions();
    Region getRegionById(Integer id);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void addRegion(String regionName);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void deleteRegion(Integer region_id);

    List<Street> getAllStreets();
    Street getStreetById(Integer id);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void addStreet(String streetName);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void deleteStreet(Integer street_id);

    void addAddress(Address address);
    @PreAuthorize("hasAuthority('MODERATOR')")
     void deleteAddress(Integer address_id);

}
