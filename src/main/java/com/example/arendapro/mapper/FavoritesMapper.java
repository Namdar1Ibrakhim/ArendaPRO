package com.example.arendapro.mapper;

import com.example.arendapro.dto.FavoritesDto;
import com.example.arendapro.entity.Favorites;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FavoritesMapper{
    FavoritesDto toDto(Favorites favorites);
    //Favorites toEntity(FavoritesRequestDto favoritesRequestDto);
}
