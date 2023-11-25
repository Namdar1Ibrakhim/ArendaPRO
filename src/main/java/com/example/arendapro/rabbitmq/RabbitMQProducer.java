package com.example.arendapro.rabbitmq;

import com.example.arendapro.entity.Immovables;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message){
        log.info(String.format("Message sent -> %s", message.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    public void sendJsonMessage(Immovables message){
        log.info(String.format("Message sent -> %s", message.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, message);
    }



}
