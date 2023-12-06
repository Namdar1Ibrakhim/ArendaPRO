package com.example.arendapro.repository;

import com.example.arendapro.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Modifying
    @Query("DELETE FROM Address e WHERE e.immovables.id = :immovableId")
    void deleteByImmovableId(Integer immovableId);
}
