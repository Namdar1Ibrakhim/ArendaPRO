package com.example.arendapro.service.address;

import com.example.arendapro.entity.address.Country;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CountryService {

    List<Country> getAllCountries();
    Country getCountryById(Integer id);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void addCountry(String iso, String countryName);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void deleteCountry(Integer country_id);
}
