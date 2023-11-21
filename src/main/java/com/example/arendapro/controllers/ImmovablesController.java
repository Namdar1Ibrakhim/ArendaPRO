package com.example.arendapro.controllers;

import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.exceptions.AccessDeniedException;
import com.example.arendapro.model.ImmovableWithCountView;
import com.example.arendapro.security.user.User;
import com.example.arendapro.service.ImmovablesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/immovables")
public class ImmovablesController {

    private final ImmovablesService immovablesService;

    @PostMapping("/add")
    public ResponseEntity<ImmovableResponseDto> addImmovable(@RequestBody ImmovableRequestDto immovablesDto, @AuthenticationPrincipal User user) throws IOException {
        log.info("Log: " + immovablesDto.toString());
        return ResponseEntity.ok(immovablesService.addImmovable(immovablesDto, user));
    }

    @DeleteMapping("{immovables_id}")
    public ResponseEntity<String> deleteImmovable(@PathVariable Integer immovables_id, @AuthenticationPrincipal User user) throws Exception {
        immovablesService.deleteImmovable(immovables_id, user);
        return ResponseEntity.ok(immovablesService.deleteImmovable(immovables_id, user));

    }
    @PostMapping("/edit/{immovable_id}")
    public ResponseEntity<ImmovableResponseDto> editImmovable(@RequestBody ImmovableRequestDto immovableRequestDto, @PathVariable Integer immovable_id, @AuthenticationPrincipal User user) throws AccessDeniedException, IOException {
        return ResponseEntity.ok(immovablesService.editImmovable(immovable_id ,immovableRequestDto, user));
    }

    @GetMapping("")
    public ResponseEntity<ImmovableWithCountView> getByPageAndLimit(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                    @RequestParam(value = "limit", defaultValue = "25") int limit){
        List<ImmovableResponseDto> list = immovablesService.getAllImmovables(page, limit);
        return ResponseEntity.ok(new ImmovableWithCountView(list, list.size()));
    }

    @RequestMapping("/{immovables_id}")
    public ResponseEntity<ImmovableResponseDto> getImmovable(@PathVariable Integer immovables_id){
        return ResponseEntity.ok(immovablesService.findImmovable(immovables_id));
    }

    @GetMapping("/myImmovables")
    public ResponseEntity<List<ImmovableResponseDto>> getAllMyImmovables(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(immovablesService.findMyImmovables(user));
    }

    @RequestMapping("/byOwner/{owner_id}")
    public ResponseEntity<List<ImmovableResponseDto>> getAllImmovablesByOwner_id(@PathVariable Integer owner_id){
        return ResponseEntity.ok(immovablesService.findImmovablesByOwner(owner_id));
    }


}
