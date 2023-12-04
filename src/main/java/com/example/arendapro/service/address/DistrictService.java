package com.example.arendapro.service.address;

import com.example.arendapro.entity.address.District;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DistrictService {

    List<District> getAllDistricts();
    District getDistrictsById(Integer id);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void addDistrict(String districtName);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void deleteDistrict(Integer district_id);
}
