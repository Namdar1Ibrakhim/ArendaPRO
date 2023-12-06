package com.example.arendapro.controllers;

import com.example.arendapro.dto.FavoritesDto;
import com.example.arendapro.entity.User;
import com.example.arendapro.exceptions.AccessDeniedException;
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
    public ResponseEntity<List<FavoritesDto>> getAllMyFavorites(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(favoritesService.getAllMyFavorites(user));
    }
    @RequestMapping("/add/{immovable_id}")
    public ResponseEntity<FavoritesDto> addFavorites(@PathVariable Integer immovable_id, @AuthenticationPrincipal User user){
        return ResponseEntity.ok(favoritesService.addFavorites(immovable_id, user));
    }
    @RequestMapping("/delete/{favorites_id}")
    public ResponseEntity deleteFavorites(@PathVariable Integer favorites_id, @AuthenticationPrincipal User user) throws AccessDeniedException {
        favoritesService.deleteFavorites(favorites_id, user);
        return new ResponseEntity(HttpStatus.OK);
    }


}
