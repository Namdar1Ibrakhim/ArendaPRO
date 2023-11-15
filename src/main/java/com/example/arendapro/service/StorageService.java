package com.example.arendapro.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    String uploadImage(MultipartFile file) throws IOException;
    byte[] downloadImage(Integer id);
}
