package com.example.arendapro.controllers;

import com.example.arendapro.dto.UserDto;
import com.example.arendapro.mapper.UserMapper;
import com.example.arendapro.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController{

    private final UserProfileService userProfileService;
    private final UserMapper mapper;

    @PostMapping("")
    public ResponseEntity<UserDto> userProfile(@RequestBody Integer user_id){
        UserDto userProfileDto = mapper.toDto(userProfileService.getUserDetailsById(user_id).get());
        return ResponseEntity.ok(userProfileDto);
    }
    @GetMapping("")
    public ResponseEntity<UserDto> myProfile(){
        UserDto userProfileDto = mapper.toDto(userProfileService.getCurrentUserDetails().get());
        return ResponseEntity.ok(userProfileDto);
    }
}
