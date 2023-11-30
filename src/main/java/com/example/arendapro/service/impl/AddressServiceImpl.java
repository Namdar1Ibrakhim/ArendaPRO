package com.example.arendapro.service.impl;

import com.example.arendapro.dto.AddressRequestDto;
import com.example.arendapro.entity.address.*;
import com.example.arendapro.exceptions.EntityNotFoundException;
import com.example.arendapro.repository.*;
import com.example.arendapro.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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
    @Cacheable(value = "AddressService::getCityById", key = "#id")
    public City getCityById(Integer id) {
        return cityRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("City not fount with id: " + id));
    }

    @Override
    @Transactional
    public void addCity(String cityName) {
        cityRepository.setCity(cityName);
    }

    @Override
    @Transactional
    public void deleteCity(Integer city_id) {
        cityRepository.deleteById(city_id);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    @Cacheable(value = "AddressService::getCountryById", key = "#id")
    public Country getCountryById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Country not fount with id: " + id));
    }


    @Override
    @Transactional
    public void addCountry(String iso, String countryName) {
        countryRepository.setCountry(iso, countryName);
    }

    @Transactional
    @Override
    public void deleteCountry(Integer country_id) {
        countryRepository.deleteById(country_id);

    }

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    @Cacheable(value = "AddressService::getDistrictsById", key = "#id")
    public District getDistrictsById(Integer id) {
        return districtRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("District not fount with id: " + id));
    }

    @Transactional
    @Override
    public void addDistrict(String districtName){
        districtRepository.setDistrict(districtName);

    }

    @Override
    @Transactional
    public void deleteDistrict(Integer district_id) {
        districtRepository.deleteById(district_id);
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    @Cacheable(value = "AddressService::getRegionById", key = "#id")
    public Region getRegionById(Integer id) {
        return regionRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Region not fount with id: " + id));
    }

    @Override
    @Transactional
    public void addRegion(String regionName) {
        regionRepository.setRegion(regionName);
    }

    @Override
    @Transactional
    public void deleteRegion(Integer region_id) {
        regionRepository.deleteById(region_id);
    }

    @Override
    public List<Street> getAllStreets() {
        return streetRepository.findAll();
    }

    @Override
    @Cacheable(value = "AddressService::getStreetById", key = "#id")
    public Street getStreetById(Integer id) {
        return streetRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Street not fount with id: " + id));
    }

    @Override
    @Transactional
    public void addStreet(String streetName) {
        streetRepository.setStreet(streetName);
    }

    @Override
    @Transactional
    public void deleteStreet(Integer street_id) {
        streetRepository.deleteById(street_id);

    }

    @Override
    @Transactional
    public void addAddress(Address address) {
        addressRepository.save(address);
        log.info("Адрес добавлен "+ address.toString());
    }

    @Override
    @Transactional
    public void deleteAddress(Integer address_id) {
        addressRepository.deleteById(address_id);
    }


}
