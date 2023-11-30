package com.example.arendapro.service.impl;

import com.example.arendapro.dto.FavoritesDto;
import com.example.arendapro.entity.Favorites;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.exceptions.EntityNotFoundException;
import com.example.arendapro.mapper.FavoritesMapper;
import com.example.arendapro.repository.FavoritesRepository;
import com.example.arendapro.repository.ImmovablesRepository;
import com.example.arendapro.entity.User;
import com.example.arendapro.service.FavoritesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoritesServiceImpl implements FavoritesService {

    private final FavoritesRepository favoritesRepository;
    private final FavoritesMapper favoritesMapper;
    private final ImmovablesRepository immovablesRepository;

    @Override
    public List<FavoritesDto> getAllMyFavorites(User user) {
        List<FavoritesDto> list = new ArrayList<>();
        for(Favorites favorites: favoritesRepository.findAllByUser_Id(user.getId())){
            list.add(favoritesMapper.toDto(favorites));
        }
        return list;
    }

    @Override
    @Transactional
    public FavoritesDto addFavorites(Integer immovable_id, User user) {
        Immovables immovable = immovablesRepository.findById(immovable_id)
                .orElseThrow(() -> new EntityNotFoundException("Immovable not fount with id: " + immovable_id));

        Favorites favorites =
                Favorites.builder().
                immovable(immovable)
                .user(user)
                .build();
        favoritesRepository.save(favorites);
        return favoritesMapper.toDto(favorites);
    }

    @Override
    @Transactional
    public void deleteFavorites(Integer favorites_id) {
        favoritesRepository.deleteById(favorites_id);
    }
}
