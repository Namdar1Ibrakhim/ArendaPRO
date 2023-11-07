package com.example.arendapro.repository;

import com.example.arendapro.entity.address.City;
import com.example.arendapro.entity.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    List<Country> findAll();



}
