package com.example.arendapro.security.auth;

import com.example.arendapro.openfeign.GeocodeClient;
import com.example.arendapro.openfeign.GeocoderResponse.GeocodeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthenticationService service;
    private final GeocodeClient geoсoderClient;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisterRequest request
    ){
        return ResponseEntity.ok(service.registerUser(request));

    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/zapr")
    public ResponseEntity<GeocodeResponse> geocoderResponseResponseEntity() throws UnsupportedEncodingException, URISyntaxException {
        String location = "Уральск,+Алаш+улица,+дом+46";

        String apiKey = "095d27ea-89d5-48f7-904a-e3d9545c03f5";

        GeocodeResponse geocodeResponse = geoсoderClient.geocode(apiKey, location, "json");
//        log.info();
        log.info("Кодировка" + geocodeResponse.toString());
        return ResponseEntity.ok(geoсoderClient.geocode(apiKey, location, "json"));
    }
    //   https://geocode-maps.yandex.ru/1.x/?apikey=095d27ea-89d5-48f7-904a-e3d9545c03f5&geocode=%D0%A3%D1%80%D0%B0%D0%BB%D1%8C%D1%81%D0%BA,+%D1%83%D0%BB%D0%B8%D1%86%D0%B0+%D0%90%D0%B7%D0%B5%D1%80%D0%B1%D0%B0%D0%B9%D0%B4%D0%B6%D0%B0%D0%BD%D0%B0-%D0%9C%D0%B0%D0%BC%D0%B5%D1%82%D0%BE%D0%B2%D0%B0,+%D0%B4%D0%BE%D0%BC+37/1&format=json

}
