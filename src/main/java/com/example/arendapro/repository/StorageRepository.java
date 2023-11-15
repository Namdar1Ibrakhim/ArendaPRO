package com.example.arendapro.repository;

import com.example.arendapro.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<ImageData, Integer>{

    Optional<ImageData> findByName(String fileName);

}
