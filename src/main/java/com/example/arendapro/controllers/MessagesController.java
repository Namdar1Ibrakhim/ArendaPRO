package com.example.arendapro.controllers;

import com.example.arendapro.dto.MessageRequestDto;
import com.example.arendapro.dto.MessageResponseDto;
import com.example.arendapro.entity.Messages;
import com.example.arendapro.exceptions.UserNotFoundException;
import com.example.arendapro.security.user.User;
import com.example.arendapro.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/messages")
public class MessagesController {

    private final MessageService messageService;

    @GetMapping("")
    public ResponseEntity<List<MessageResponseDto>> getAllMessages(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(messageService.getAllMyMessage(user));
    }
    @PostMapping("/send")
    public ResponseEntity sendMessage(@RequestBody MessageRequestDto messageRequstDto, @AuthenticationPrincipal User sender) throws UserNotFoundException {
        messageService.sendMessage(messageRequstDto, sender);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/{user_id}")
    public ResponseEntity<List<Messages>> getMessagesByUser(@PathVariable Integer user_id, @AuthenticationPrincipal User user){
        return ResponseEntity.ok(messageService.getMessagesByUser(user_id, user));
    }

}
