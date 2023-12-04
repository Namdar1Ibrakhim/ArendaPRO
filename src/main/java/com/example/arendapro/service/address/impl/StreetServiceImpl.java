package com.example.arendapro.service.address.impl;

import com.example.arendapro.entity.address.Street;
import com.example.arendapro.exceptions.EntityNotFoundException;
import com.example.arendapro.repository.StreetRepository;
import com.example.arendapro.service.address.StreetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class StreetServiceImpl implements StreetService {

    private final StreetRepository streetRepository;

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
}
