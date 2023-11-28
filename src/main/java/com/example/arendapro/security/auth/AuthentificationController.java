package com.example.arendapro.security.auth;

import com.example.arendapro.openfeign.GeocoderResponse.GeocoderResponse;
import com.example.arendapro.openfeign.YandexGeoсoderExample;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthenticationService service;
    private final YandexGeoсoderExample geoсoderExample;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.registerUser(request));

    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/zapr")
    public ResponseEntity<GeocoderResponse> geocoderResponseResponseEntity(){
        return ResponseEntity.ok(geoсoderExample.geocodeAddress("095d27ea-89d5-48f7-904a-e3d9545c03f5", "Уральск,+улица+Алаш,+дом+46"));
    }


}
