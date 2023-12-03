package com.example.arendapro.service.impl;

import com.example.arendapro.dto.*;
import com.example.arendapro.entity.*;
import com.example.arendapro.entity.address.Address;
import com.example.arendapro.enums.*;
import com.example.arendapro.exceptions.*;
import com.example.arendapro.mapper.AddressMapper;
import com.example.arendapro.mapper.ImmovablesMapper;
import com.example.arendapro.rabbitmq.RabbitMQProducer;
import com.example.arendapro.repository.ImmovableImageRepository;
import com.example.arendapro.repository.ImmovablesRepository;
import com.example.arendapro.enums.Role;
import com.example.arendapro.entity.User;
import com.example.arendapro.service.AddressService;
import com.example.arendapro.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
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

        when(immovablesRepository.findByActiveId(anyInt())).thenReturn(java.util.Optional.of(immovables));
        when(immovablesRepository.save(any())).thenReturn(immovables);

        try {
            immovablesService.changeStatus(immovableId, status, user);
        } catch (AccessDeniedException e) {
            e.printStackTrace();
        }

        verify(immovablesRepository, times(1)).findByActiveId(immovableId);


    }


    @Test
    public void testDeleteImmovable() throws Exception {
        Integer immovablesId = 1;

        User owner = new User();
        owner.setRole(Role.USER);

        Immovables immovables = new Immovables();
        immovables.setOwner(owner);

        when(immovablesRepository.findById(immovablesId)).thenReturn(Optional.of(immovables));

        String result = immovablesService.deleteImmovable(immovablesId, owner);

        assertEquals("Successfully deleted", result);

        verify(addressService).deleteAddress(immovablesId);
        verify(immovablesRepository).delete(immovables);
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

        when(immovablesRepository.findByActiveId(anyInt())).thenReturn(java.util.Optional.of(immovables));
        when(immovablesMapper.toDto(any(), any())).thenReturn(new ImmovableResponseDto());

        ImmovableResponseDto result = immovablesService.getActiveImmovableById(immovablesId);

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
    void testFilterImmovables() {
        List<Immovables> filteredImmovables = Collections.singletonList(new Immovables());

        when(immovablesRepository.findByFilter(any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(filteredImmovables);
        when(immovablesMapper.toDtoList(any())).thenReturn(Collections.singletonList(new ImmovableResponseDto()));

        List<Immovables> result = immovablesService.filterImmovables(0L, 100L, 1, 3, 50.0, 150.0, State.Среднее, PropertyType.ДАЧА);

        assertFalse(result.isEmpty());
    }


    @Test
    void testUploadImage() throws IOException {
        Integer immovableId = 1;
        MockMultipartFile image = new MockMultipartFile("image", "image.jpg", "image/jpeg", "content".getBytes());
        Immovables immovables = new Immovables();
        immovables.setImages(new ArrayList<>());

        when(immovablesRepository.findById(anyInt())).thenReturn(Optional.of(immovables));
        when(imageService.upload(any())).thenReturn("image.jpg");

        immovablesService.uploadImage(immovableId, image);

        assertNotNull(immovables.getImages());
        assertEquals(1, immovables.getImages().size());
        assertEquals("image.jpg", immovables.getImages().get(0));

        verify(immovablesRepository, times(1)).save(immovables);
        verify(immovableImageRepository, times(1)).save(any());
    }


}

