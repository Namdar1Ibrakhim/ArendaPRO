package com.example.arendapro.controllers;

import com.example.arendapro.dto.FavoritesRequestDto;
import com.example.arendapro.dto.FavoritesResponseDto;
import com.example.arendapro.service.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FavoritesController {

    private final FavoritesService favoritesService;

    @GetMapping()
    public ResponseEntity<List<FavoritesResponseDto>> getAllMyFavorites(){
        return ResponseEntity.ok(favoritesService.getAllMyFavorites());
    }
}
