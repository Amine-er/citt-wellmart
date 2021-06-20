package com.citt.wellmart.controller;


import com.citt.wellmart.controller.models.AuthorityDto;
import com.citt.wellmart.controller.models.RoleDto;
import com.citt.wellmart.controller.models.UserDto;
import com.citt.wellmart.services.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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


@CrossOrigin("*")
@RestController
@Tag(name = "Roles Management")
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RoleController {

    @NonNull
    RoleService roleService ;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") UUID id){
        return new ResponseEntity<>(roleService.getRoleById(id),HttpStatus.OK);
    }

    @DeleteMapping(("/{id}"))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable("id") UUID id){
        roleService.deleteRoleById(id);
    }

    @PutMapping
    public ResponseEntity updateRole(@RequestBody @Valid RoleDto roleDto){
        roleService.updateRole(roleDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{roleId}/authorities")
    public ResponseEntity addAuthoritiesToRole(@PathVariable("roleId")  UUID roleId,@RequestBody List<AuthorityDto> authorities){
        roleService.addAuthorityToRole(roleId,authorities);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<UserDto> saveRole(@RequestBody @Valid  RoleDto roleDto){
        RoleDto dto = roleService.saveRole(roleDto);
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
