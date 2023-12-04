package com.example.arendapro.controllers;

import com.example.arendapro.entity.address.*;
import com.example.arendapro.repository.CityRepository;
import com.example.arendapro.service.address.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/address")
public class AddressController {

    private final AddressService addressService;
    private final CountryService countryService;
    private final CityService cityService;
    private final DistrictService districtService;
    private final RegionService regionService;
    private final StreetService streetService;


    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCities(){
        return ResponseEntity.ok(cityService.getAllCities());
    }
    @RequestMapping("/city/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Integer id){
        return ResponseEntity.ok(cityService.getCityById(id));
    }
    @RequestMapping("/city/add")
    public ResponseEntity addCity(@RequestParam String cityName){
        cityService.addCity(cityName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/city/{city_id}")
    public ResponseEntity deleteCity(@PathVariable Integer city_id){
        cityService.deleteCity(city_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getCountries(){
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @RequestMapping("/country/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Integer id){
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @RequestMapping("/country/add")
    public ResponseEntity addCountry(@RequestParam String iso, @RequestParam String countryName){
        countryService.addCountry(iso, countryName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/country/{country_id}")
    public ResponseEntity deleteCountry(@PathVariable Integer country_id){
        countryService.deleteCountry(country_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/districts")
    public ResponseEntity<List<District>> getDistricts(){
        return ResponseEntity.ok(districtService.getAllDistricts());
    }
    @RequestMapping("/districts/{id}")
    public ResponseEntity<District> getDistrictsById(@PathVariable Integer id){
        return ResponseEntity.ok(districtService.getDistrictsById(id));
    }

    @RequestMapping("/district/add")
    public ResponseEntity addDistrict(@RequestParam String districtName){
        districtService.addDistrict(districtName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/district/{district_id}")
    public ResponseEntity deleteDistrict(@PathVariable Integer district_id){
        districtService.deleteDistrict(district_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/regions")
    public ResponseEntity<List<Region>> getRegions(){
        return ResponseEntity.ok(regionService.getAllRegions());
    }
    @RequestMapping("/region/{id}")
    public ResponseEntity<Region> getRegionsById(@PathVariable Integer id){
        return ResponseEntity.ok(regionService.getRegionById(id));
    }

    @RequestMapping("/region/add")
    public ResponseEntity addRegion(@RequestParam String regionName){
        regionService.addRegion(regionName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/region/{region_id}")
    public ResponseEntity deleteRegion(@PathVariable Integer region_id){
        regionService.deleteRegion(region_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/streets")
    public ResponseEntity<List<Street>> getStreets(){
        return ResponseEntity.ok(streetService.getAllStreets());
    }
    @RequestMapping("/street/{id}")
    public ResponseEntity<Street> getStreetById(@PathVariable Integer id){
        return ResponseEntity.ok(streetService.getStreetById(id));
    }
    @RequestMapping("/street/add")
    public ResponseEntity addStreet(@RequestParam String streetName){
        streetService.addStreet(streetName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/street/{street_id}")
    public ResponseEntity deleteStreet(@PathVariable Integer street_id){
        streetService.deleteStreet(street_id);
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
