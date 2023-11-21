package com.example.arendapro.repository;

import com.example.arendapro.entity.address.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends JpaRepository<Street, Integer> {

    @Modifying
    @Query("INSERT INTO Street(name) VALUES (:name)")
    void setStreet(@Param("name") String name);
}
