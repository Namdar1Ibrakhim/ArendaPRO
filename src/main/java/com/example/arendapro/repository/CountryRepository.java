package com.example.arendapro.repository;

import com.example.arendapro.entity.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    List<Country> findAll();

    @Modifying
    @Query("INSERT INTO Country (iso, name) VALUES (:iso, :name)")
    void setCountry(@Param("iso") String iso, @Param("name") String name);
}
