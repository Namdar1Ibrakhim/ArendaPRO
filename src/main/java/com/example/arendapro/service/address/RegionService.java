package com.example.arendapro.service.address;

import com.example.arendapro.entity.address.Region;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RegionService {

    List<Region> getAllRegions();
    Region getRegionById(Integer id);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void addRegion(String regionName);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void deleteRegion(Integer region_id);

}
