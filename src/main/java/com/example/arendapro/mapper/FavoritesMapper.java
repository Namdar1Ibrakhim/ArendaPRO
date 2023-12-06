package com.example.arendapro.mapper;

import com.example.arendapro.dto.AddressResponseDto;
import com.example.arendapro.dto.FavoritesDto;
import com.example.arendapro.entity.Favorites;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.entity.address.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FavoritesMapper{

    @Mapping(target = "user_id", expression = "java(getUserId(favorites))")
    @Mapping(target = "immovableId", expression = "java(getImmovableId(favorites))")
    FavoritesDto toDto(Favorites favorites);

    Favorites toEntity(FavoritesDto favoritesRequestDto);

    default Integer getUserId(Favorites favorites){
        if(favorites == null){
            return null;
        }
        return favorites.getUser().getId();
    }

    default Integer getImmovableId(Favorites favorites){
        if(favorites == null){
            return null;
        }
        return favorites.getImmovable().getId();
    }
}
