package com.example.arendapro.service.address;

import com.example.arendapro.entity.address.Street;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface StreetService {

    List<Street> getAllStreets();
    Street getStreetById(Integer id);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void addStreet(String streetName);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void deleteStreet(Integer street_id);

}
