package com.citt.wellmart.controller;


import com.citt.wellmart.controller.models.AuthorityDto;
import com.citt.wellmart.controller.models.UserDto;
import com.citt.wellmart.services.AuthorityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;


@RestController
@Tag(name = "Authorities Management")
@RequestMapping("/api/v1/authorities")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthorityController {

    @NonNull
    AuthorityService authorityService ;

    @GetMapping
    public ResponseEntity<List<AuthorityDto>> getAllAuthorities(){
        return new ResponseEntity<>(authorityService.getAllAuthorities(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorityDto> getAuthorityById(@PathVariable("id") UUID id){
        return new ResponseEntity<>(authorityService.getAuthorityById(id),HttpStatus.OK);
    }

    @DeleteMapping(("/{id}"))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthority(@PathVariable("id") UUID id){
        authorityService.deleteAuthorityById(id);
    }

    @PutMapping
    public ResponseEntity updateAuthority(@RequestBody @Valid  AuthorityDto authorityDto){
        authorityService.updateAuthority(authorityDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<UserDto> saveAuthority(@RequestBody @Valid AuthorityDto authorityDto){
        AuthorityDto dto = authorityService.saveAuthority(authorityDto);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        headers.setLocation(location);
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }
}
