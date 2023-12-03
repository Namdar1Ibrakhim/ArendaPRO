package com.example.arendapro.controllers;

import com.example.arendapro.entity.address.*;
import com.example.arendapro.service.AddressService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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
    @RequestMapping("/city/add")
    public ResponseEntity addCity(@RequestParam String cityName){
        addressService.addCity(cityName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/city/{city_id}")
    public ResponseEntity deleteCity(@PathVariable Integer city_id){
        addressService.deleteCity(city_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getCountries(){
        return ResponseEntity.ok(addressService.getAllCountries());
    }

    @RequestMapping("/country/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.getCountryById(id));
    }

    @RequestMapping("/country/add")
    public ResponseEntity addCountry(@RequestParam String iso, @RequestParam String countryName){
        addressService.addCountry(iso, countryName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/country/{country_id}")
    public ResponseEntity deleteCountry(@PathVariable Integer country_id){
        addressService.deleteCountry(country_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/districts")
    public ResponseEntity<List<District>> getDistricts(){
        return ResponseEntity.ok(addressService.getAllDistricts());
    }
    @RequestMapping("/districts/{id}")
    public ResponseEntity<District> getDistrictsById(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.getDistrictsById(id));
    }

    @RequestMapping("/district/add")
    public ResponseEntity addDistrict(@RequestParam String districtName){
        addressService.addDistrict(districtName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/district/{district_id}")
    public ResponseEntity deleteDistrict(@PathVariable Integer district_id){
        addressService.deleteDistrict(district_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/regions")
    public ResponseEntity<List<Region>> getRegions(){
        return ResponseEntity.ok(addressService.getAllRegions());
    }
    @RequestMapping("/region/{id}")
    public ResponseEntity<Region> getRegionsById(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.getRegionById(id));
    }

    @RequestMapping("/region/add")
    public ResponseEntity addRegion(@RequestParam String regionName){
        addressService.addRegion(regionName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/region/{region_id}")
    public ResponseEntity deleteRegion(@PathVariable Integer region_id){
        addressService.deleteRegion(region_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/streets")
    public ResponseEntity<List<Street>> getStreets(){
        return ResponseEntity.ok(addressService.getAllStreets());
    }
    @RequestMapping("/street/{id}")
    public ResponseEntity<Street> getStreetById(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.getStreetById(id));
    }
    @RequestMapping("/street/add")
    public ResponseEntity addStreet(@RequestParam String streetName){
        addressService.addStreet(streetName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/street/{street_id}")
    public ResponseEntity deleteStreet(@PathVariable Integer street_id){
        addressService.deleteStreet(street_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{address_id}")
    public ResponseEntity deleteAddress(@PathVariable Integer address_id){
        addressService.deleteAddress(address_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @PostMapping("/address")
//    public ResponseEntity addAddress(@RequestBody AddressDto addressDto){
//
//    }




}
