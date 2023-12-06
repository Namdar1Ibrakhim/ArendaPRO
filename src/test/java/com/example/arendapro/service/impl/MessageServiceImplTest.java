package com.example.arendapro.service.impl;
import com.example.arendapro.dto.MessageRequestDto;
import com.example.arendapro.dto.MessageResponseDto;
import com.example.arendapro.entity.Messages;
import com.example.arendapro.entity.User;
import com.example.arendapro.exceptions.UserNotFoundException;
import com.example.arendapro.mapper.MessageMapper;
import com.example.arendapro.repository.MessageRepository;
import com.example.arendapro.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private MessageMapper messageMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllMyMessage() {
        User user = new User();
        when(messageRepository.findAllByReceiver_Id(user.getId())).thenReturn(Collections.emptyList());

        List<MessageResponseDto> result = messageService.getAllMyMessage(user);

        assertEquals(0, result.size());
    }

    @Test
    void testSendMessageSuccess() throws UserNotFoundException {
        MessageRequestDto requestDto = new MessageRequestDto();
        requestDto.setReceiver_id(1);
        User user = new User();

        when(userRepository.findById(requestDto.getReceiver_id())).thenReturn(Optional.of(new User()));
        when(messageMapper.toEntity(requestDto, userRepository)).thenReturn(new Messages());

        messageService.sendMessage(requestDto, user);

        verify(userRepository, times(1)).findById(requestDto.getReceiver_id());
        verify(messageRepository, times(1)).save(any(Messages.class));
    }

    @Test
    void testSendMessageUserNotFound() {
        MessageRequestDto requestDto = new MessageRequestDto();
        requestDto.setReceiver_id(1);
        User user = new User();

        when(userRepository.findById(requestDto.getReceiver_id())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> messageService.sendMessage(requestDto, user));
    }

    @Test
    void testGetMessagesByUser() {
        User sender = new User();
        User receiver = new User();
        when(messageRepository.findAllByReceiver_IdAndSender_Id(receiver.getId(), sender.getId()))
                .thenReturn(Collections.emptyList());

        List<MessageResponseDto> result = messageService.getMessagesByUser(sender.getId(), receiver);

        assertEquals(0, result.size());
    }

    @Test
    void testGetMessagesByUserWithMessages() {
        User sender = new User();
        User receiver = new User();
        Messages message = new Messages();

        when(messageRepository.findAllByReceiver_IdAndSender_Id(receiver.getId(), sender.getId()))
                .thenReturn(Collections.singletonList(message));

        when(messageMapper.toDtoList(Collections.singletonList(message)))
                .thenReturn(Collections.singletonList(new MessageResponseDto()));

        List<MessageResponseDto> result = messageService.getMessagesByUser(sender.getId(), receiver);

        assertEquals(1, result.size());

        verify(messageMapper, times(1)).toDtoList(Collections.singletonList(message));
        verify(messageMapper, times(0)).toDto(any()); // Убедимся, что метод toDto не вызывался
    }


}
