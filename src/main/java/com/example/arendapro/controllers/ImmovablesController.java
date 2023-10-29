package com.example.arendapro.controllers;

import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.service.ImmovablesService;
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
    public ResponseEntity<ImmovableResponseDto> addImmovable(@RequestBody ImmovableRequestDto immovablesDto){
        return ResponseEntity.ok(immovablesService.addImmovable(immovablesDto));
    }
    @DeleteMapping("{immovables_id}")
    public ResponseEntity deleteImmovable(@PathVariable Integer immovables_id) throws Exception {
        immovablesService.deleteImmovable(immovables_id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<ImmovableResponseDto>> getAllImmovables(){
        return ResponseEntity.ok(immovablesService.getAllImmovables());
    }

    @RequestMapping("/{immovables_id}")
    public ResponseEntity<ImmovableResponseDto> getImmovable(@PathVariable Integer immovables_id){
        return ResponseEntity.ok(immovablesService.findImmovable(immovables_id));
    }

    @GetMapping("/myImmovables")
    public ResponseEntity<List<ImmovableResponseDto>> getAllMyImmovables(){
        return ResponseEntity.ok(immovablesService.findMyImmovables());
    }

    @RequestMapping("/byOwner/{owner_id}")
    public ResponseEntity<List<ImmovableResponseDto>> getAllMyImmovables(@PathVariable Integer owner_id){
        return ResponseEntity.ok(immovablesService.findImmovablesByOwner(owner_id));
    }


}
