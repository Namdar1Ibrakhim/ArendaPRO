package com.example.arendapro.controllers;

import com.example.arendapro.dto.FavoritesRequestDto;
import com.example.arendapro.dto.FavoritesResponseDto;
import com.example.arendapro.security.user.User;
import com.example.arendapro.service.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/favorites")
public class FavoritesController {

    private final FavoritesService favoritesService;

    @GetMapping()
    public ResponseEntity<List<FavoritesResponseDto>> getAllMyFavorites(){
        return ResponseEntity.ok(favoritesService.getAllMyFavorites());
    }
    @RequestMapping("/add/{immovable_id}")
    public ResponseEntity<FavoritesResponseDto> addFavorites(@PathVariable Integer immovable_id, @AuthenticationPrincipal User user){
        return ResponseEntity.ok(favoritesService.addFavorites(immovable_id, user));
    }
    @DeleteMapping("/delete/favorites_id")
    public ResponseEntity deleteFavorites(@PathVariable Integer favorites_id){
        favoritesService.deleteFavorites(favorites_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
