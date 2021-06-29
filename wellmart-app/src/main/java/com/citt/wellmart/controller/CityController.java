package com.citt.wellmart.controller;

import com.citt.wellmart.entities.City;
import com.citt.wellmart.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping
    public City saveCity(@RequestBody City city){ return cityService.saveCity(city);}

    @GetMapping
    public List<City> getAllCity(){
        return cityService.getAllCity();
    }

    @DeleteMapping("/{id}")
    public void deleteCityById(@PathVariable(name = "id") Long id){ cityService.deleteCityById(id); }
}
