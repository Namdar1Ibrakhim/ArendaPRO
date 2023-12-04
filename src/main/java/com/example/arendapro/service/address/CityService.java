package com.example.arendapro.service.address;

import com.example.arendapro.entity.address.City;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CityService {

    List<City> getAllCities();
    City getCityById(Integer id);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void addCity(String cityName);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void deleteCity(Integer city_id);
}
