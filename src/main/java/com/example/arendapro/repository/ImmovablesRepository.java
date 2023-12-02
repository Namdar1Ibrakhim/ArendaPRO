package com.example.arendapro.repository;

import com.example.arendapro.entity.Immovables;
import com.example.arendapro.entity.User;
import com.example.arendapro.enums.PropertyType;
import com.example.arendapro.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

@Repository
public interface ImmovablesRepository extends JpaRepository<Immovables, Integer>{

    @Query("SELECT i FROM Immovables i WHERE i.id =:id and i.status = 'ACTIVE'")
    Optional<Immovables> findByActiveId(Integer id);

    @Query("SELECT i FROM Immovables i WHERE i.owner =:owner")
    List<Immovables> findImmovablesByOwner(User owner);

    @Query("SELECT i FROM Immovables i WHERE i.owner.id =:owner_id and i.status='ACTIVE'")
    List<Immovables> findImmovablesByOwner_Id(Integer owner_id);

    @Query("SELECT i FROM Immovables i where i.status ='ACTIVE' ORDER BY i.createdAt DESC")
    List<Immovables> findByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT i FROM Immovables i " +
            "WHERE (:minPrice IS NULL OR i.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR i.price <= :maxPrice) " +
            "AND (:minNumOfRooms IS NULL OR i.numOfRooms >= :minNumOfRooms) " +
            "AND (:maxNumOfRooms IS NULL OR i.numOfRooms <= :maxNumOfRooms) " +
            "AND (:minArea IS NULL OR i.area >= :minArea) " +
            "AND (:maxArea IS NULL OR i.area <= :maxArea) " +
            "AND (:state IS NULL OR i.state = :state) " +
            "AND (:propertyType IS NULL OR i.propertyType = :propertyType)" +
            "AND i.status = 'ACTIVE'")
    List<Immovables> findByFilter(Long minPrice, Long maxPrice, Integer minNumOfRooms, Integer maxNumOfRooms, Double minArea, Double maxArea, State state, PropertyType propertyType);
}
