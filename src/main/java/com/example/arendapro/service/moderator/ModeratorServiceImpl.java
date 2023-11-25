package com.example.arendapro.service.moderator;

import com.example.arendapro.entity.Immovables;
import com.example.arendapro.enums.Status;
import com.example.arendapro.exceptions.EntityNotFoundException;
import com.example.arendapro.rabbitmq.RabbitMQConsumer;
import com.example.arendapro.repository.ImmovablesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModeratorServiceImpl implements ModeratorService{

    private final RabbitMQConsumer consumer;
    private final ImmovablesRepository immovablesRepository;

    @Override
    public Immovables getWaitingImmovable() {
        String id = consumer.getMessageFromQueue();
        if(id==null || id=="") throw new EntityNotFoundException("Queue is empty");

        Immovables immovable = immovablesRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new EntityNotFoundException("Immovable not fount with id: " + id));
        return immovable;
    }

    @Override
    @Transactional
    public void changeImmovableStatus(Integer immovable_id, Status status) {
        Immovables immovable = immovablesRepository.findById(immovable_id)
                .orElseThrow(() -> new EntityNotFoundException("Immovable not fount with id: " + immovable_id));
        immovable.setStatus(status);
    }
}
