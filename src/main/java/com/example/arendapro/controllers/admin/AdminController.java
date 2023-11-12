package com.example.arendapro.controllers.admin;

import com.example.arendapro.dto.UserDto;
import com.example.arendapro.security.auth.AuthenticationService;
import com.example.arendapro.security.auth.RegisterRequest;
import com.example.arendapro.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController{

    private final AuthenticationService authenticationService;

    @PostMapping("/createModerator")
    public ResponseEntity createModeratorUser(@RequestBody RegisterRequest registerRequest){
        authenticationService.registerUser(registerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
