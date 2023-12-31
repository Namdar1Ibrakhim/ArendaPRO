package com.example.arendapro.service;

import com.example.arendapro.dto.FavoritesDto;
import com.example.arendapro.entity.User;
import com.example.arendapro.exceptions.AccessDeniedException;

import java.util.List;

public interface FavoritesService {
    List<FavoritesDto> getAllMyFavorites(User user);
    FavoritesDto addFavorites(Integer immovable_id, User user);
    void deleteFavorites(Integer favorites_id, User user) throws AccessDeniedException;
}
