package com.citt.wellmart.services;

import com.citt.wellmart.entities.Country;
import com.citt.wellmart.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository ;

    public Country saveCountry(Country country){
       return   countryRepository.save(country);
    }

    public List<Country> getAllCountry(){
       return countryRepository.findAll();
    }

    public void deleteCountryById(Long id){
        countryRepository.deleteById(id);
    }
}
