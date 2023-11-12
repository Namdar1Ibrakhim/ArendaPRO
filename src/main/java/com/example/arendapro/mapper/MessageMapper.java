package com.example.arendapro.mapper;

import com.example.arendapro.dto.MessageRequestDto;
import com.example.arendapro.dto.MessageResponseDto;
import com.example.arendapro.entity.Messages;
import com.example.arendapro.security.user.User;
import com.example.arendapro.security.user.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    List<MessageResponseDto> toDtoList(List<Messages> messages);

    @Mapping(target = "sender_id", expression = "java(mapToSender_id(messages))")
    @Mapping(target = "receiver_id", expression = "java(mapToReceiver_id(messages))")
    MessageResponseDto toDto(Messages messages);

    @Mapping(target = "receiver", expression = "java(mapToUser(messageRequestDto, userRepository))")
    Messages toEntity(MessageRequestDto messageRequestDto, UserRepository userRepository);

    default User mapToUser(MessageRequestDto messageRequestDto, UserRepository userRepository) {
        if (messageRequestDto == null || messageRequestDto.getReceiver_id() == null) {
            return null;
        }
        return userRepository.findById(messageRequestDto.getReceiver_id()).orElse(null);
    }
    default Integer mapToSender_id(Messages messages) {
        if (messages == null) {
            return null;
        }
        return messages.getSender().getId();
    }
    default Integer mapToReceiver_id(Messages messages){
        if (messages == null) {
            return null;
        }
        return messages.getReceiver().getId();
    }
}
