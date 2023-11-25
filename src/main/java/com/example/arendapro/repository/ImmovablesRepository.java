package com.example.arendapro.repository;

import com.example.arendapro.entity.Immovables;
import com.example.arendapro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

@Repository
public interface ImmovablesRepository extends JpaRepository<Immovables, Integer>{

    @Query("SELECT i FROM Immovables i WHERE i.id =:id and i.status = 'ACTIVE'")
    Optional<Immovables> findById(Integer id);

    @Query("SELECT i FROM Immovables i WHERE i.owner =:owner")
    List<Immovables> findImmovablesByOwner(User owner);

    @Query("SELECT i FROM Immovables i WHERE i.owner.id =:owner_id and i.status='ACTIVE'")
    List<Immovables> findImmovablesByOwner_Id(Integer owner_id);

    @Query("SELECT i FROM Immovables i where i.status ='ACTIVE' ORDER BY i.createdAt DESC")
    List<Immovables> findByOrderByCreatedAtDesc(Pageable pageable);


}
