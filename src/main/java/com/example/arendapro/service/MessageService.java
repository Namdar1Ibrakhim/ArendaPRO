package com.example.arendapro.service;


import com.example.arendapro.dto.MessageRequestDto;
import com.example.arendapro.dto.MessageResponseDto;
import com.example.arendapro.entity.Messages;
import com.example.arendapro.exceptions.UserNotFoundException;
import com.example.arendapro.entity.User;

import java.util.List;

public interface MessageService {

    List<MessageResponseDto> getAllMyMessage(User user);
    List<Messages> getMessagesByUser(Integer id, User user);
    void sendMessage(MessageRequestDto messageDto, User user) throws UserNotFoundException;

}
