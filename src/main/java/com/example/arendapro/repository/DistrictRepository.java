package com.example.arendapro.repository;

import com.example.arendapro.entity.address.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Modifying
    @Query("INSERT INTO district values(:name)")
    void setDistrict(String name);
}
