package com.example.arendapro.repository;

import com.example.arendapro.entity.ImmovableImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImmovableImageRepository extends JpaRepository<ImmovableImage, Integer> {


}
