package com.example.arendapro.controllers;

import com.example.arendapro.dto.UserDto;
import com.example.arendapro.mapper.UserMapper;
import com.example.arendapro.security.user.User;
import com.example.arendapro.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController{

    private final UserProfileService userProfileService;

    @RequestMapping("{user_id}")
    public ResponseEntity<UserDto> userProfile(@PathVariable Integer user_id){
        UserDto userProfileDto = userProfileService.getUserDetailsById(user_id);
        return ResponseEntity.ok(userProfileDto);
    }
    @GetMapping("")
    public ResponseEntity<UserDto> myProfile(){
        UserDto userProfileDto = userProfileService.getCurrentUserDetails();
        return ResponseEntity.ok(userProfileDto);
    }
    @PostMapping("/update")
    public ResponseEntity updateUserProfile(@RequestBody UserDto userDto){
        userProfileService.updateUserProfile(userDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
