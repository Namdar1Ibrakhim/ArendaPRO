package com.example.arendapro.service.address.impl;

import com.example.arendapro.entity.address.Country;
import com.example.arendapro.exceptions.EntityNotFoundException;
import com.example.arendapro.repository.CountryRepository;
import com.example.arendapro.service.address.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

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
}
