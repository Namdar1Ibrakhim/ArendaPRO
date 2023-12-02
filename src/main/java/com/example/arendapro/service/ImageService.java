package com.example.arendapro.service;

import com.example.arendapro.dto.ImmovableImageRequestDto;
import com.example.arendapro.entity.ImmovableImage;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String upload(MultipartFile image);
}
