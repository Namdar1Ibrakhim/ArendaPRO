package com.example.arendapro.service.impl;

import com.example.arendapro.entity.Immovables;
import com.example.arendapro.enums.Status;
import com.example.arendapro.exceptions.EntityNotFoundException;
import com.example.arendapro.rabbitmq.RabbitMQConsumer;
import com.example.arendapro.repository.ImmovablesRepository;
import com.example.arendapro.service.moderator.ModeratorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModeratorServiceImplTest {

    @Mock
    private RabbitMQConsumer consumer;

    @Mock
    private ImmovablesRepository immovablesRepository;

    @InjectMocks
    private ModeratorServiceImpl moderatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWaitingImmovable() {
        String messageId = "1";
        Immovables expectedImmovable = new Immovables();
        when(consumer.getMessageFromQueue()).thenReturn(messageId);
        when(immovablesRepository.findById(Integer.parseInt(messageId))).thenReturn(java.util.Optional.of(expectedImmovable));

        Immovables result = moderatorService.getWaitingImmovable();

        assertNotNull(result);
        assertEquals(expectedImmovable, result);
    }

    @Test
    void testGetWaitingImmovableEmptyQueue() {
        when(consumer.getMessageFromQueue()).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> moderatorService.getWaitingImmovable());
    }

    @Test
    void testGetWaitingImmovableNotFound() {
        String messageId = "1";
        when(consumer.getMessageFromQueue()).thenReturn(messageId);
        when(immovablesRepository.findById(Integer.parseInt(messageId))).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> moderatorService.getWaitingImmovable());
    }

    @Test
    @Transactional
    void testChangeImmovableStatus() {
        // Arrange
        Integer immovableId = 1;
        Status newStatus = Status.ACTIVE;
        Immovables immovable = new Immovables();
        when(immovablesRepository.findById(immovableId)).thenReturn(java.util.Optional.of(immovable));

        // Act
        moderatorService.changeImmovableStatus(immovableId, newStatus);

        // Assert
        assertEquals(newStatus, immovable.getStatus());
    }
    @Test
    void testChangeImmovableStatusNotFound() {
        Integer immovableId = 1;
        Status newStatus = Status.ACTIVE;
        when(immovablesRepository.findById(immovableId)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> moderatorService.changeImmovableStatus(immovableId, newStatus));
    }
}
