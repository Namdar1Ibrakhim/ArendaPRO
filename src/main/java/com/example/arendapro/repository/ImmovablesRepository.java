package com.example.arendapro.repository;

import com.example.arendapro.entity.Immovables;
import com.example.arendapro.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Repository
public interface ImmovablesRepository extends JpaRepository<Immovables, Integer>{

    List<Immovables> findImmovablesByOwner(User owner);

    List<Immovables> findImmovablesByOwner_Id(Integer owner_id);

    List<Immovables> findByOrderByCreatedAtDesc(Pageable pageable);


}
