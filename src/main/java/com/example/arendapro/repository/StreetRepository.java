package com.example.arendapro.repository;

import com.example.arendapro.entity.address.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends JpaRepository<Street, Integer> {

    @Modifying
    @Query("INSERT INTO street values(:name)")
    void setStreet(String name);
}
