package com.example.arendapro.rabbitmq;

import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.mapper.ImmovablesMapper;
import com.example.arendapro.rabbitmq.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RabbitMessageController {

    private final RabbitMQProducer producer;
    private final ImmovablesMapper immovablesMapper;

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody String message){
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }

    @PostMapping("/publishJson")
     public ResponseEntity<String> sendJsonMessage(@RequestBody ImmovableRequestDto immovableRequestDto){
        producer.sendJsonMessage(immovablesMapper.toEntity(immovableRequestDto));
        return ResponseEntity.ok("Message json sent to RabbitMQ ...");
     }

}
