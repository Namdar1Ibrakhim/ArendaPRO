package com.example.arendapro.service.address.impl;

import com.example.arendapro.entity.address.Region;
import com.example.arendapro.exceptions.EntityNotFoundException;
import com.example.arendapro.repository.RegionRepository;
import com.example.arendapro.service.address.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

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

}
