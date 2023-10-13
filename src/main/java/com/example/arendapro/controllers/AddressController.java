package com.example.arendapro.controllers;

import com.example.arendapro.dto.AddressDto;
import com.example.arendapro.entity.address.*;
import com.example.arendapro.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;
    private final StreetRepository streetRepository;


    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCities(){
        return ResponseEntity.ok(cityRepository.findAll());
    }
    @RequestMapping("/city/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Integer id){
        return ResponseEntity.ok(cityRepository.findById(id).get());
    }
    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getCountries(){
        return ResponseEntity.ok(countryRepository.findAll());
    }
    @RequestMapping("/country/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Integer id){
        return ResponseEntity.ok(countryRepository.findById(id).get());
    }
    @GetMapping("/districts")
    public ResponseEntity<List<District>> getDistricts(){
        return ResponseEntity.ok(districtRepository.findAll());
    }
    @RequestMapping("/districts/{id}")
    public ResponseEntity<District> getDistrictsById(@PathVariable Integer id){
        return ResponseEntity.ok(districtRepository.findById(id).get());
    }
    @GetMapping("/regions")
    public ResponseEntity<List<Region>> getRegions(){
        return ResponseEntity.ok(regionRepository.findAll());
    }
    @RequestMapping("/region/{id}")
    public ResponseEntity<Region> getRegionsById(@PathVariable Integer id){
        return ResponseEntity.ok(regionRepository.findById(id).get());
    }
    @GetMapping("/streets")
    public ResponseEntity<List<Street>> getStreets(){
        return ResponseEntity.ok(streetRepository.findAll());
    }
    @RequestMapping("/street/{id}")
    public ResponseEntity<Street> getStreetById(@PathVariable Integer id){
        return ResponseEntity.ok(streetRepository.findById(id).get());
    }

//    @PostMapping("/address")
//    public ResponseEntity addAddress(@RequestBody AddressDto addressDto){
//
//    }




}
