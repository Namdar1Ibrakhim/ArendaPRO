package com.example.arendapro.repository;

import com.example.arendapro.entity.address.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    @Modifying
    @Query("INSERT INTO region values(:name)")
    void setRegion(String name);
}
