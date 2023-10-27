package com.example.arendapro.service.impl;

import com.example.arendapro.dto.FavoritesResponseDto;
import com.example.arendapro.entity.Favorites;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.mapper.FavoritesMapper;
import com.example.arendapro.repository.FavoritesRepository;
import com.example.arendapro.repository.ImmovablesRepository;
import com.example.arendapro.security.user.User;
import com.example.arendapro.security.user.UserRepository;
import com.example.arendapro.service.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritesServiceImpl implements FavoritesService {

    private final FavoritesRepository favoritesRepository;
    private final UserRepository userRepository;
    private final FavoritesMapper favoritesMapper;
    private final ImmovablesRepository immovablesRepository;

    @Override
    public List<FavoritesResponseDto> getAllMyFavorites() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName()).get();

        List<FavoritesResponseDto> list = new ArrayList<>();
        for(Favorites favorites: favoritesRepository.findAllByUser_Id(user.getId())){
            list.add(favoritesMapper.toDto(favorites));
        }
        return list;
    }

    @Override
    public FavoritesResponseDto addFavorites(Integer immovable_id, User user) {
        Immovables immovables = immovablesRepository.findById(immovable_id).get();
        Favorites favorites =
                Favorites.builder().
                immovable(immovables)
                .user(user)
                .build();
        favoritesRepository.save(favorites);
        return favoritesMapper.toDto(favorites);

    }

    @Override
    public void deleteFavorites(Integer favorites_id) {
        favoritesRepository.deleteById(favorites_id);
    }
}
