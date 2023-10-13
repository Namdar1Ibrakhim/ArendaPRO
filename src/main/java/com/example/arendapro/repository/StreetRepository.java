package com.example.arendapro.repository;

import com.example.arendapro.entity.address.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends JpaRepository<Street, Integer> {
}
