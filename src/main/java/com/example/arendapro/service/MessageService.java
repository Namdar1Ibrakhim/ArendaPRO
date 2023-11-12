package com.example.arendapro.service;


import com.example.arendapro.dto.MessageRequestDto;
import com.example.arendapro.dto.MessageResponseDto;
import com.example.arendapro.entity.Messages;
import com.example.arendapro.security.user.User;

import java.util.List;

public interface MessageService {

    List<MessageResponseDto> getAllMyMessage(User user);
    List<Messages> getMessagesByReceiver(Integer id, User user);
    void sendMessage(MessageRequestDto messageDto, User user);

}
