package com.example.arendapro.service.impl;

import com.example.arendapro.entity.ImageData;
import com.example.arendapro.repository.StorageRepository;
import com.example.arendapro.service.StorageService;
import com.example.arendapro.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final StorageRepository repository;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if(imageData!=null){
            return "file uploaded successfully: " + file.getOriginalFilename();
        }
        return null;
    }
    public byte[] downloadImage(Integer id){
        Optional<ImageData> dbImageData = repository.findById(id);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
