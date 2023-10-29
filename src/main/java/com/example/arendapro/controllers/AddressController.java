package com.example.arendapro.controllers;

import com.example.arendapro.entity.address.*;
import com.example.arendapro.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCities(){
        return ResponseEntity.ok(addressService.getAllCities());
    }
    @RequestMapping("/city/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.getCityById(id));
    }
    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getCountries(){
        return ResponseEntity.ok(addressService.getAllCountries());
    }
    @RequestMapping("/country/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.getCountryById(id));
    }
    @GetMapping("/districts")
    public ResponseEntity<List<District>> getDistricts(){
        return ResponseEntity.ok(addressService.getAllDistricts());
    }
    @RequestMapping("/districts/{id}")
    public ResponseEntity<District> getDistrictsById(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.getDistrictsById(id));
    }
    @GetMapping("/regions")
    public ResponseEntity<List<Region>> getRegions(){
        return ResponseEntity.ok(addressService.getAllRegions());
    }
    @RequestMapping("/region/{id}")
    public ResponseEntity<Region> getRegionsById(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.getRegionById(id));
    }
    @GetMapping("/streets")
    public ResponseEntity<List<Street>> getStreets(){
        return ResponseEntity.ok(addressService.getAllStreets());
    }
    @RequestMapping("/street/{id}")
    public ResponseEntity<Street> getStreetById(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.getStreetById(id));
    }

//    @PostMapping("/address")
//    public ResponseEntity addAddress(@RequestBody AddressDto addressDto){
//
//    }




}
