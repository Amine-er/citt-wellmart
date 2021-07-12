package com.citt.wellmart.controller;

import com.citt.wellmart.controller.mappers.MerchantMapper;
import com.citt.wellmart.controller.models.MerchantDto;
import com.citt.wellmart.controller.models.PersonDto;
import com.citt.wellmart.entities.Company;
import com.citt.wellmart.entities.Merchant;
import com.citt.wellmart.entities.Person;
import com.citt.wellmart.repositories.MerchantRepository;
import com.citt.wellmart.utils.GenerateMerchantAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/merchants")
public class MerchantController {

    @Autowired
    private MerchantRepository merchantRepository ;
    @Autowired
    private MerchantMapper merchantMapper ;
    @Autowired
    private GenerateMerchantAccount generateMerchantAccount;
    @PostMapping
    public ResponseEntity<MerchantDto> saveMerchant(@RequestBody MerchantDto merchantDto){
        if(merchantDto.getMerchantType().equals("P")){
            Person merchant = merchantMapper.toMerchant(merchantDto);
            generateMerchantAccount.generatePersonAccount(merchantDto);
            return  new ResponseEntity<>( merchantMapper.toMerchantDto(merchantRepository.save(merchant)), HttpStatus.OK);
        }else{
            Company merchant = merchantMapper.toCompany(merchantDto);
            generateMerchantAccount.generateCompanyAccount(merchantDto);
            return  new ResponseEntity<>( merchantMapper.toMerchantDto(merchantRepository.save(merchant)), HttpStatus.OK);
        }
    }
    @GetMapping
    public ResponseEntity<List<MerchantDto>> getAllMerchants(){
        List<MerchantDto> merchantDtos = new ArrayList<>();
        List<PersonDto> persons = merchantRepository.findAll().stream().filter(e-> e instanceof Person).map(e->{
           return PersonDto.builder()
                    .address(e.getAddress())
                    .description(e.getDescription())
                    .email(e.getEmail())
                    .firstName(((Person) e).getFirstName())
                    .lastName(((Person) e).getLastName())
                    .phone(e.getPhone())
                    .webSite(e.getWebSite())
                    .merchantType("Person")
                    .build();
        }).collect(Collectors.toList());

        List<MerchantDto> companies = merchantRepository.findAll().stream().filter(e-> e instanceof Company).map(e->{
          return MerchantDto.builder().merchantType("Company").activity(((Company) e).getActivity()).name(((Company) e).getName()).address(e.getAddress()).email(e.getEmail()).phone(e.getPhone()).webSite(e.getWebSite()).build();
        }).collect(Collectors.toList());
        companies.stream().forEach(e->{
          MerchantDto dto =   MerchantDto.builder().merchantType("Company").activity(e.getActivity()).name(e.getName()).address(e.getAddress()).email(e.getEmail()).phone(e.getPhone()).webSite(e.getWebSite()).build();
        merchantDtos.add(dto);
        });

        persons.stream().forEach(e->{
            MerchantDto dto = MerchantDto.builder()
                    .address(e.getAddress())
                    .description(e.getDescription())
                    .email(e.getEmail())
                    .firstName(e.getFirstName())
                    .lastName(e.getLastName())
                    .phone(e.getPhone())
                    .webSite(e.getWebSite())
                    .merchantType("Person")
                    .build();
            merchantDtos.add(dto);
        });

        return  new ResponseEntity<>(merchantDtos,HttpStatus.OK);
    }
}
