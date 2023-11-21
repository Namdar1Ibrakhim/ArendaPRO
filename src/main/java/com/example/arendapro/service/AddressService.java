package com.example.arendapro.service;

import com.example.arendapro.dto.AddressRequestDto;
import com.example.arendapro.entity.address.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface AddressService {

    List<City> getAllCities();
    City getCityById(Integer id);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    void addCity(String cityName);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    void deleteCity(Integer city_id);

    List<Country> getAllCountries();
    Country getCountryById(Integer id);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    void addCountry(String iso, String countryName);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    void deleteCountry(Integer country_id);

    List<District> getAllDistricts();
    District getDistrictsById(Integer id);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    void addDistrict(String districtName);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    void deleteDistrict(Integer district_id);

    List<Region> getAllRegions();
    Region getRegionById(Integer id);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    void addRegion(String regionName);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    void deleteRegion(Integer region_id);

    List<Street> getAllStreets();
    Street getStreetById(Integer id);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    void addStreet(String streetName);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    void deleteStreet(Integer street_id);

    void addAddress(Address address);
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    void deleteAddress(Integer address_id);

}
