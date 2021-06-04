package com.citt.wellmart.services;

import com.citt.wellmart.entities.City;
import com.citt.wellmart.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public City saveCity(City city){ return cityRepository.save(city);}

    public List<City> getAllCity(){
        return cityRepository.findAll();
    }

    public void deleteCityById(Long id){ cityRepository.deleteById(id); }
}
