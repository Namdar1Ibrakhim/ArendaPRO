package com.example.arendapro.controllers.admin;

import com.example.arendapro.dto.UserDto;
import com.example.arendapro.entity.User;
import com.example.arendapro.security.auth.AuthenticationService;
import com.example.arendapro.security.auth.RegisterRequest;
import com.example.arendapro.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController{

    private final AuthenticationService authenticationService;

    @PostMapping("/createModerator")
    public ResponseEntity createModerator(@RequestBody RegisterRequest registerRequest, @AuthenticationPrincipal User user){
        authenticationService.registerModerator(registerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity createUser(@RequestBody RegisterRequest registerRequest){
        authenticationService.registerUser(registerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/createAdmin")
    public ResponseEntity createAdmin(@RequestBody RegisterRequest registerRequest){
        authenticationService.registerAdmin(registerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
