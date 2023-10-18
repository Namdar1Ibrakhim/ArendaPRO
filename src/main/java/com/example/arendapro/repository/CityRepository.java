package com.example.arendapro.repository;

import com.example.arendapro.entity.address.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findAll();

    Optional<City> findById(Integer id);


}
