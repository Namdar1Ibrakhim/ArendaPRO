package com.example.arendapro.service;

import com.example.arendapro.dto.FavoritesResponseDto;
import com.example.arendapro.entity.Favorites;
import com.example.arendapro.security.user.User;

import java.util.List;

public interface FavoritesService {
    List<FavoritesResponseDto> getAllMyFavorites();
    FavoritesResponseDto addFavorites(Integer immovable_id, User user);
    void deleteFavorites(Integer favorites_id);
}
