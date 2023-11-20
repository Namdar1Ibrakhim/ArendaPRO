package com.example.arendapro.controllers;

import com.example.arendapro.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/image")
public class ImageController {

    private final StorageService storageService;

    @PostMapping("/{immovable_id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file, @PathVariable Integer immovable_id) throws IOException {
        String uploadImage = storageService.uploadImage(file, immovable_id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }
    @PostMapping("/uploadList/{immovable_id}")
    public ResponseEntity<?> uploadImageList(@RequestParam("images")MultipartFile[] file, @PathVariable Integer immovable_id) throws IOException {
        String uploadImageList = storageService.uploadImageList(file, immovable_id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImageList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Integer id){
        byte[] imageData = storageService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @GetMapping("/by/{immovable_id}")
    public ResponseEntity<?> getImagesByImmovable(@PathVariable Integer immovable_id){
        List<Integer> imageDataId = storageService.getImagesByImmovable(immovable_id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(imageDataId);
    }


}
