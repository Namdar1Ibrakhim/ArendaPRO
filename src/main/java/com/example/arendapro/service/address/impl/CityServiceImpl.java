package com.example.arendapro.service.address.impl;

import com.example.arendapro.entity.address.City;
import com.example.arendapro.exceptions.EntityNotFoundException;
import com.example.arendapro.repository.CityRepository;
import com.example.arendapro.service.address.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

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



}
