package com.example.arendapro.controllers;

import com.example.arendapro.dto.AddressDto;
import com.example.arendapro.dto.ImmovablesDto;
import com.example.arendapro.service.ImmovablesService;
import jakarta.transaction.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth/immovables")
public class ImmovablesController {

    private final ImmovablesService immovablesService;

    @PostMapping("/add")
    public ResponseEntity<ImmovablesDto> addImmovables(@RequestBody ImmovablesDto immovablesDto, @RequestBody AddressDto addressDto){
        return ResponseEntity.ok(immovablesService.addImmovables(immovablesDto, addressDto));
    }


}
