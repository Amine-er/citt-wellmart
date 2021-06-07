package com.citt.wellmart.controller;

import com.citt.wellmart.entities.Country;
import com.citt.wellmart.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping
    public Country saveCountry(@RequestBody Country country){
        return   countryService.saveCountry(country);
    }

    @GetMapping
    public List<Country> getAllCountry(){
        return countryService.getAllCountry();
    }
    @DeleteMapping("/{id}")
    public void deleteCountryById(@PathVariable Long id){
        countryService.deleteCountryById(id);
    }

}
