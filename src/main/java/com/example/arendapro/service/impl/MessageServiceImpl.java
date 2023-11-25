package com.example.arendapro.service.impl;

import com.example.arendapro.dto.MessageRequestDto;
import com.example.arendapro.dto.MessageResponseDto;
import com.example.arendapro.entity.Messages;
import com.example.arendapro.exceptions.UserNotFoundException;
import com.example.arendapro.mapper.MessageMapper;
import com.example.arendapro.repository.MessageRepository;
import com.example.arendapro.entity.User;
import com.example.arendapro.repository.UserRepository;
import com.example.arendapro.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final UserRepository userRepository;

    @Override
    public List<MessageResponseDto> getAllMyMessage(User user) {
        List<Messages> messages = messageRepository.findAllByReceiver_Id(user.getId());
        return messageMapper.toDtoList(messages);
    }

    @Override
    public List<Messages> getMessagesByUser(Integer id, User user) {
        return messageRepository.findAllByReceiver_IdAndSender_Id(id, user.getId());
    }

    @Override
    @Transactional
    public void sendMessage(MessageRequestDto messageRequstDto, User user) throws UserNotFoundException {
        if(!userRepository.findById(messageRequstDto.getReceiver_id()).isPresent()) throw new UserNotFoundException("Receiver not found with id: " +messageRequstDto.getReceiver_id());

        Messages messages = messageMapper.toEntity(messageRequstDto, userRepository);
        messages.setSender(user);
        messages.setCreatedAt(new Date());

        messageRepository.save(messages);
    }
}
