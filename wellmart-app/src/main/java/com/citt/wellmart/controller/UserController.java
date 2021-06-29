package com.citt.wellmart.controller;



import com.citt.wellmart.controller.models.RoleDto;
import com.citt.wellmart.controller.models.UserDto;
import com.citt.wellmart.services.UserService;
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
import java.util.Set;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@CrossOrigin("*")
@RestController
@Tag(name = "Users Management")
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserController {

    @NonNull
    UserService userService ;


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") UUID id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @DeleteMapping(("/{id}"))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") UUID id){
        userService.deleteUserById(id);
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody @Valid UserDto userDto) throws Exception {
        userService.updateUser(userDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid  UserDto userDto) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        UserDto dto = userService.saveUser(userDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        headers.setLocation(location);
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<Set<RoleDto>> getUserRolesById(@PathVariable("id") UUID id){
        return new ResponseEntity<>(userService.getUserRolesByUserId(id),HttpStatus.OK);
    }

    @PutMapping("/{id}/roles")
    public ResponseEntity addRoleToUser(@PathVariable("id") UUID id,@RequestBody List<RoleDto> roles){
        userService.addRolesToUser(id,roles);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
