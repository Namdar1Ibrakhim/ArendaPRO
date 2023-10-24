package com.example.arendapro.controllers;

import com.example.arendapro.dto.AddressDto;
import com.example.arendapro.dto.ImmovablesDto;
import com.example.arendapro.service.ImmovablesService;
import jakarta.transaction.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/immovables")
public class ImmovablesController {

    private final ImmovablesService immovablesService;

    @PostMapping("/add")
    public ResponseEntity<ImmovablesDto> addImmovable(@RequestBody ImmovablesDto immovablesDto, @RequestBody AddressDto addressDto){
        return ResponseEntity.ok(immovablesService.addImmovable(immovablesDto, addressDto));
    }
    @DeleteMapping("{immovables_id}")
    public ResponseEntity deleteImmovable(@PathVariable Integer immovables_id) throws Exception {
        immovablesService.deleteImmovable(immovables_id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<ImmovablesDto>> getAllImmovables(){
        return ResponseEntity.ok(immovablesService.getAllImmovables());
    }

    @RequestMapping("/{immovables_id}")
    public ResponseEntity<ImmovablesDto> getImmovable(@PathVariable Integer immovables_id){
        return ResponseEntity.ok(immovablesService.findImmovable(immovables_id));
    }

    @GetMapping("/myImmovables")
    public ResponseEntity<List<ImmovablesDto>> getAllMyImmovables(){
        return ResponseEntity.ok(immovablesService.findMyImmovables());
    }

    @RequestMapping("/byOwner/{owner_id}")
    public ResponseEntity<List<ImmovablesDto>> getAllMyImmovables(@PathVariable Integer owner_id){
        return ResponseEntity.ok(immovablesService.findImmovablesByOwner(owner_id));
    }


}
