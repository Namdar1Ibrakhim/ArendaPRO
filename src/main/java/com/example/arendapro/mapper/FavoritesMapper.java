package com.example.arendapro.mapper;

import com.example.arendapro.dto.FavoritesRequestDto;
import com.example.arendapro.dto.FavoritesResponseDto;
import com.example.arendapro.entity.Favorites;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FavoritesMapper{
    FavoritesResponseDto toDto(Favorites favorites);
    Favorites toEntity(FavoritesRequestDto favoritesRequestDto);
}
