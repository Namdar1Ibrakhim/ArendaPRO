//package com.example.arendapro.mapper.impl;
//
//import com.example.arendapro.dto.FavoritesRequestDto;
//import com.example.arendapro.dto.FavoritesResponseDto;
//import com.example.arendapro.entity.Favorites;
//import com.example.arendapro.mapper.FavoritesMapper;
//import com.example.arendapro.mapper.ImmovablesMapper;
//import com.example.arendapro.repository.ImmovablesRepository;
//import com.example.arendapro.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class FavoritesMapperImpl implements FavoritesMapper {
//
//    private final ImmovablesMapper immovablesMapper;
//    private final ImmovablesRepository immovablesRepository;
//    private final UserRepository userRepository;
//
//    @Override
//    public FavoritesResponseDto toDto(Favorites favorites) {
//        FavoritesResponseDto favoritesResponseDto = new FavoritesResponseDto();
//        favoritesResponseDto.setUser_id(favorites.getUser().getId());
//        favoritesResponseDto.setImmovablesDto(immovablesMapper.toDto(favorites.getImmovable()));
//        return favoritesResponseDto;
//    }
//
//    @Override
//    public Favorites toEntity(FavoritesRequestDto favoritesRequestDto) {
//        Favorites favorites = new Favorites();
//        favorites.setImmovable(immovablesRepository.findById(favoritesRequestDto.getImmovable_id()).get());
//        favorites.setUser(userRepository.findById(favoritesRequestDto.getUser_id()).get());
//        return favorites;
//    }
//}
