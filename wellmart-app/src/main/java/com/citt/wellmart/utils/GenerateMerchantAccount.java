package com.citt.wellmart.utils;

import com.citt.wellmart.controller.models.MerchantDto;
import com.citt.wellmart.entities.security.Authority;
import com.citt.wellmart.entities.security.Role;
import com.citt.wellmart.entities.security.User;
import com.citt.wellmart.repositories.AuthorityRepository;
import com.citt.wellmart.repositories.RoleRepository;
import com.citt.wellmart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class GenerateMerchantAccount {

    @Autowired
    private RoleRepository roleRepository ;
    @Autowired
    private UserRepository userRepository;
    public void  generatePersonAccount(MerchantDto merchantDto){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user1 = User.builder()
                .username(merchantDto.getFirstName()+"."+merchantDto.getLastName())
                .password(passwordEncoder.encode("admin"))
                .firstName(merchantDto.getFirstName())
                .lastName(merchantDto.getLastName())
                .email("admin@gmail.com")
                .phone("0607707989")
                .role(roleRepository.findByName("MERCHANT"))
                .build();

        userRepository.save(user1);
    }
    public void  generateCompanyAccount(MerchantDto merchantDto){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user1 = User.builder()
                .username(merchantDto.getName())
                .password(passwordEncoder.encode("admin"))
                .firstName(merchantDto.getFirstName())
                .lastName(merchantDto.getLastName())
                .email("admin@gmail.com")
                .phone("0607707989")
                .role(roleRepository.findByName("MERCHANT"))
                .build();

        userRepository.save(user1);
    }

}
