package com.example.arendapro.service.address.impl;

import com.example.arendapro.entity.address.District;
import com.example.arendapro.exceptions.EntityNotFoundException;
import com.example.arendapro.repository.DistrictRepository;
import com.example.arendapro.service.address.DistrictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

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


}
