package com.example.arendapro.service.impl;
import com.example.arendapro.dto.FavoritesDto;
import com.example.arendapro.entity.Favorites;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.entity.User;
import com.example.arendapro.exceptions.EntityNotFoundException;
import com.example.arendapro.mapper.FavoritesMapper;
import com.example.arendapro.repository.FavoritesRepository;
import com.example.arendapro.repository.ImmovablesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FavoritesServiceImplTest {

    @Mock
    private FavoritesRepository favoritesRepository;

    @Mock
    private FavoritesMapper favoritesMapper;

    @Mock
    private ImmovablesRepository immovablesRepository;

    @InjectMocks
    private FavoritesServiceImpl favoritesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFavorites() {
        Integer immovableId = 1;
        User user = new User();

        Immovables immovable = new Immovables();
        when(immovablesRepository.findById(immovableId)).thenReturn(Optional.of(immovable));

        FavoritesDto expectedFavoritesDto = new FavoritesDto();
        when(favoritesMapper.toDto(any())).thenReturn(expectedFavoritesDto);

        FavoritesDto resultFavoritesDto = favoritesService.addFavorites(immovableId, user);

        assertEquals(expectedFavoritesDto, resultFavoritesDto);

        verify(favoritesRepository, times(1)).save(any());
    }

    @Test
    void testAddFavorites_ImmovableNotFound() {
        Integer immovableId = 1;
        User user = new User();

        when(immovablesRepository.findById(immovableId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> favoritesService.addFavorites(immovableId, user));

        // Verify that the repository save method was not called
        verify(favoritesRepository, never()).save(any());
    }
    @Test
    void testDeleteFavorites() {
        Integer favoritesId = 1;

        favoritesService.deleteFavorites(favoritesId);

        verify(favoritesRepository, times(1)).deleteById(favoritesId);
    }

    @Test
    void testGetAllMyFavorites() {
        User user = new User();

        List<Favorites> favoritesList = new ArrayList<>();
        when(favoritesRepository.findAllByUser_Id(user.getId())).thenReturn(favoritesList);

        FavoritesDto expectedFavoritesDto = new FavoritesDto();
        when(favoritesMapper.toDto(any())).thenReturn(expectedFavoritesDto);

        List<FavoritesDto> resultFavoritesDtoList = favoritesService.getAllMyFavorites(user);

        assertEquals(favoritesList.size(), resultFavoritesDtoList.size());

        verify(favoritesRepository, times(1)).findAllByUser_Id(user.getId());
    }

}
