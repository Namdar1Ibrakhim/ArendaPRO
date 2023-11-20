package com.example.arendapro.service;

import com.example.arendapro.entity.Immovables;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {

    String uploadImage(MultipartFile file, Integer immovable_id) throws IOException;
    byte[] downloadImage(Integer id);
    List<Integer> getImagesByImmovable(Integer immovable_id);
    String uploadImageList(MultipartFile[] file, Integer immovable_id) throws IOException;
    void deleteImageById(Integer image_id);
}
