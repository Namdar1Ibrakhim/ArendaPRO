package com.example.arendapro.service.impl;

import com.example.arendapro.entity.ImageData;
import com.example.arendapro.repository.ImmovablesRepository;
import com.example.arendapro.repository.StorageRepository;
import com.example.arendapro.service.StorageService;
import com.example.arendapro.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final StorageRepository storageRepository;
    private final ImmovablesRepository immovablesRepository;

    @Override
    @Transactional
    public String uploadImage(MultipartFile file, Integer immovable_id) throws IOException {
        ImageData imageData = storageRepository.save(ImageData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .immovable(immovablesRepository.findById(immovable_id).get())
                    .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if(imageData!=null){
            return "file uploaded successfully: " + file.getOriginalFilename();
        }
        return null;
    }

    @Override
    @Transactional
    public String uploadImageList(MultipartFile[] files, Integer immovable_id) throws IOException {
        for (MultipartFile file : files) {
             storageRepository.save(ImageData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                     .immovable(immovablesRepository.findById(immovable_id).get())
                    .imageData(ImageUtils.compressImage(file.getBytes())).build());
        }
        return "files uploaded successfully";

    }

    @Override
    public void deleteImageById(Integer image_id) {
        storageRepository.delete(storageRepository.findById(image_id).get());
    }

    @Override
    public void deleteImageByImmovable_id(Integer immovable_id) {
        storageRepository.deleteByImmovable_Id(immovable_id);
    }

    public byte[] downloadImage(Integer id){
        Optional<ImageData> dbImageData = storageRepository.findById(id);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    @Override
    public List<Integer> getImagesByImmovable(Integer immovable_id) {
        List<ImageData> dbImageDataList = storageRepository.findImageDataByImmovable_id(immovable_id);
        List<Integer> listOfId = dbImageDataList.stream()
                .map(ImageData::getId)
                .collect(Collectors.toList());
        return listOfId;
    }

}

















