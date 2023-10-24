package com.example.arendapro.service;

import com.example.arendapro.dto.FavoritesResponseDto;
import com.example.arendapro.entity.Favorites;

import java.util.List;

public interface FavoritesService {
    List<FavoritesResponseDto> getAllMyFavorites();

}
