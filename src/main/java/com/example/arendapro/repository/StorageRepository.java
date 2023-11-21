package com.example.arendapro.repository;

import com.example.arendapro.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface StorageRepository extends JpaRepository<ImageData, Integer>{


    Optional<ImageData> findByName(String fileName);

    List<ImageData> findImageDataByImmovable_id(Integer immovable_id);

    void deleteByImmovable_Id(Integer immovable_id);

}
