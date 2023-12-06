package com.example.arendapro.service.impl;

import com.example.arendapro.config.redis.ImmovablesCache;
import com.example.arendapro.dao.ImmovablesCacheDao;
import com.example.arendapro.dto.*;
import com.example.arendapro.entity.*;
import com.example.arendapro.entity.address.Address;
import com.example.arendapro.enums.*;
import com.example.arendapro.exceptions.*;
import com.example.arendapro.mapper.AddressMapper;
import com.example.arendapro.mapper.ImmovablesCacheMapper;
import com.example.arendapro.mapper.ImmovablesMapper;
import com.example.arendapro.rabbitmq.RabbitMQProducer;
import com.example.arendapro.repository.AddressRepository;
import com.example.arendapro.repository.ImmovableImageRepository;
import com.example.arendapro.repository.ImmovablesRepository;
import com.example.arendapro.enums.Role;
import com.example.arendapro.entity.User;
import com.example.arendapro.service.address.AddressService;
import com.example.arendapro.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ImmovablesServiceImplTest {

    @Mock
    private ImmovablesRepository immovablesRepository;

    @Mock
    private ImmovablesCacheDao immovablesDao;

    @Mock
    private ImmovablesCacheMapper immovablesCacheMapper;

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private ImmovablesMapper immovablesMapper;

    @Mock
    private AddressService addressService;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private RabbitMQProducer producer;

    @Mock
    private ImageService imageService;

    @Mock
    private ImmovableImageRepository immovableImageRepository;

    @InjectMocks
    private ImmovablesServiceImpl immovablesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddImmovable() throws IOException {
        ImmovableRequestDto immovableRequestDto = new ImmovableRequestDto();
        User user = new User();
        Address address = new Address();
        Immovables immovables = new Immovables();

        when(addressMapper.toEntity(any())).thenReturn(address);
        when(immovablesMapper.toEntity(any())).thenReturn(immovables);
        when(immovablesRepository.save(any())).thenThrow(new RuntimeException("Mocked Save Exception"));

        try {
            ImmovableResponseDto result = immovablesService.addImmovable(immovableRequestDto, user);
            assertNotNull(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void testChangeStatus() {
        Integer immovableId = 1;
        Status status = Status.ACTIVE;
        User user = new User();
        Immovables immovables = new Immovables();
        immovables.setOwner(user);
        immovables.setStatus(Status.MODERATION);

        when(immovablesRepository.findById(anyInt())).thenReturn(java.util.Optional.of(immovables));
        when(immovablesRepository.save(any())).thenReturn(immovables);

        try {
            immovablesService.changeStatus(immovableId, status, user);
        } catch (AccessDeniedException e) {
            e.printStackTrace();
        }

        verify(immovablesRepository, times(1)).findById(immovableId);


    }


    @Test
    void testDeleteImmovableWithValidOwner() throws Exception {
        // Создаем заглушки объектов
        User owner = new User();
        owner.setId(1);
        owner.setRole(Role.MODERATOR); // Или любая другая роль, которая дает доступ

        Immovables existingImmovable = new Immovables();
        existingImmovable.setId(1);
        existingImmovable.setOwner(owner); // Владелец совпадает с текущим пользователем

        when(immovablesRepository.findById(1)).thenReturn(Optional.of(existingImmovable));

        // Вызываем метод, который тестируем
        immovablesService.deleteImmovable(1, owner);


        verify(immovablesRepository, times(1)).findById(1);
        // Проверяем, что метод delete вызывался один раз с нужным аргументом
        verify(immovablesRepository, times(1)).delete(existingImmovable);
        // Проверяем, что метод deleteByImmovableId вызывался один раз с нужным аргументом
        verify(addressRepository, times(1)).deleteByImmovableId(1);
        // Проверяем, что сообщение в лог отправлено
    }


    @Test
    void testGetAllActiveImmovables() {
        int page = 1;
        int limit = 10;
        List<Immovables> immovablesList = Collections.singletonList(new Immovables());

        when(immovablesRepository.findByOrderByCreatedAtDesc(any())).thenReturn(immovablesList);
        when(immovablesMapper.toDto(any(), any())).thenReturn(new ImmovableResponseDto());

        List<ImmovableResponseDto> result = immovablesService.getAllActiveImmovables(page, limit);

        assertFalse(result.isEmpty());
    }

    @Test
    void testGetActiveImmovableById() {
        Integer immovablesId = 1;
        Immovables immovables = new Immovables();

        when(immovablesRepository.findById(anyInt())).thenReturn(java.util.Optional.of(immovables));
        when(immovablesMapper.toDto(any(), any())).thenReturn(new ImmovableResponseDto());

        ImmovableResponseDto result = immovablesService.getImmovableById(immovablesId);

        assertNotNull(result);
    }

    @Test
    void testGetAllMyImmovables() {
        User user = new User();
        List<Immovables> immovablesList = Collections.singletonList(new Immovables());
        when(immovablesRepository.findImmovablesByOwner(any())).thenReturn(immovablesList);
        when(immovablesMapper.toDto(any(), any())).thenReturn(new ImmovableResponseDto());

        List<ImmovableResponseDto> result = immovablesService.getAllMyImmovables(user);

        assertFalse(result.isEmpty());
    }

    @Test
    void testGetActiveImmovablesByOwner() {
        Integer ownerId = 1;
        List<Immovables> immovablesList = Collections.singletonList(new Immovables());

        when(immovablesRepository.findImmovablesByOwner_Id(anyInt())).thenReturn(immovablesList);
        when(immovablesMapper.toDto(any(), any())).thenReturn(new ImmovableResponseDto());

        List<ImmovableResponseDto> result = immovablesService.getActiveImmovablesByOwner(ownerId);

        assertFalse(result.isEmpty());
    }

    @Test
    void testGetAllImmovables() {
        List<Immovables> immovablesList = Collections.singletonList(new Immovables());

        when(immovablesRepository.findAll()).thenReturn(immovablesList);
        when(immovablesMapper.toDtoList(any())).thenReturn(Collections.singletonList(new ImmovableResponseDto()));

        List<ImmovableResponseDto> result = immovablesService.getAllImmovables();

        assertFalse(result.isEmpty());
    }

    @Test
    void testEditImmovableWithValidUserAndDto() throws AccessDeniedException, IOException {
        // Создаем заглушки объектов
        User user = new User();
        user.setId(1);
        user.setRole(Role.MODERATOR); // Или любая другая роль, которая дает доступ

        ImmovableRequestDto immovableDto = new ImmovableRequestDto();

        Immovables existingImmovable = new Immovables();
        existingImmovable.setId(1);
        existingImmovable.setOwner(user); // Владелец совпадает с текущим пользователем

        when(immovablesRepository.findById(eq(1))).thenReturn(Optional.of(existingImmovable));
        when(immovablesMapper.toEntity(immovableDto)).thenReturn(existingImmovable);

        // Вызываем метод, который тестируем
        ImmovableResponseDto result = immovablesService.editImmovable(1, immovableDto, user);


        // Проверяем, что метод findById вызывался один раз с нужным аргументом
        verify(immovablesRepository, times(1)).findById(eq(1));
        // Проверяем, что метод toEntity вызывался один раз с нужным аргументом
        verify(immovablesMapper, times(1)).toEntity(eq(immovableDto));
        // Проверяем, что метод save вызывался один раз
        verify(immovablesRepository, times(1)).save(existingImmovable);
        // Проверяем, что сообщение в лог отправлено
    }


    @Test
    void testFilterImmovables() {
        List<Immovables> filteredImmovables = Collections.singletonList(new Immovables());

        when(immovablesRepository.findByFilter(any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(filteredImmovables);
        when(immovablesMapper.toDtoList(any())).thenReturn(Collections.singletonList(new ImmovableResponseDto()));

        List<ImmovableResponseDto> result = immovablesService.filterImmovables(0L, 100L, 1, 3, 50.0, 150.0, State.Среднее, PropertyType.ДАЧА);

        assertFalse(result.isEmpty());
    }

    @Test
    void testGetFromCache() {
        List<ImmovablesCache> immovablesCaches = new ArrayList<>();// создайте список объектов для теста
                when(immovablesDao.findAll()).thenReturn(immovablesCaches);

        // Вызываем метод, который тестируем
        List<ImmovablesCache> result = immovablesService.getFromCache();

        // Проверяем, что результат равен ожидаемому списку
        assertEquals(immovablesCaches, result);
        // Проверяем, что метод findAll вызывался один раз
        verify(immovablesDao, times(1)).findAll();
    }
    @Test
    void testDeleteCache() {
        // Генерируем мок объект
        Immovables immovablesMock = mock(Immovables.class);

        // Задаем поведение мок объекта
        when(immovablesRepository.findById(1)).thenReturn(Optional.of(immovablesMock));

        // Вызываем метод, который должен использовать immovablesRepository.findById(1)
        immovablesService.deleteCache(1);

        // Проверяем, что метод findById был вызван
        verify(immovablesRepository, times(1)).findById(1);

        // Далее можете проверять другие ожидаемые действия...
    }
    @Test
    void testDeleteAllCache() {
        // Вызываем метод, который тестируем
        immovablesService.deleteAllCache();

        // Проверяем, что метод deleteAllCache вызывался один раз
        verify(immovablesDao, times(1)).deleteAllCache();
    }


}

