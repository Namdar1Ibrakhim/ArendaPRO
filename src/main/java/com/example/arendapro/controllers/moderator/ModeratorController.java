package com.example.arendapro.controllers.moderator;

import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.dto.StatusDto;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.rabbitmq.RabbitMQConsumer;
import com.example.arendapro.service.moderator.ModeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moderator")
public class ModeratorController {

    private final ModeratorService service;

    @GetMapping("/getWaitingImmovables")
    public ResponseEntity<ImmovableResponseDto> getWaitingImmovables(){
        return ResponseEntity.ok(service.getWaitingImmovable());
    }

    @RequestMapping("/changeStatus/{immovableId}")
    public ResponseEntity changeImmovableStatus(@PathVariable Integer immovableId, @RequestBody StatusDto statusDto){
        service.changeImmovableStatus(immovableId, statusDto.getStatus());
        return new ResponseEntity(HttpStatus.OK);
    }

}
