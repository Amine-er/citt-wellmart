package com.citt.wellmart.controller;


import com.citt.wellmart.controller.mappers.UserMapper;
import com.citt.wellmart.controller.models.AuthResponseDto;
import com.citt.wellmart.controller.models.UserDto;
import com.citt.wellmart.entities.security.User;
import com.citt.wellmart.services.UserAuthenticationService;
import com.citt.wellmart.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@Tag(name = "Authentication Management")
@RequestMapping("/public/v1/users")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
final class AuthenticationController {

    @NonNull
    UserAuthenticationService authentication;
    @NonNull
    UserService userService;
    @NonNull
    UserMapper userMapper;


    @PostMapping("/register")
    ResponseEntity<UserDto> register(@RequestBody UserDto userDto) throws Exception {
        return new ResponseEntity<>( userService.saveUser(userDto),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<AuthResponseDto> login(@RequestParam("username") final String username,
                                          @RequestParam("password") final String password) {

        return new ResponseEntity<>(authentication.login(username,password,null), HttpStatus.OK);
    }
    @GetMapping("/current")
    UserDto getCurrent(@AuthenticationPrincipal final User user) {
        return userMapper.userToUserDto(user);
    }

    @GetMapping("/logout")
    boolean logout(@AuthenticationPrincipal final User user) {
        authentication.logout(user);
        return true;
    }
}