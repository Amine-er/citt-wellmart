package com.citt.wellmart.services.jwt;


import com.citt.wellmart.controller.models.AuthResponseDto;
import com.citt.wellmart.entities.security.User;
import com.citt.wellmart.services.UserAuthenticationService;
import com.citt.wellmart.services.UserService;
import com.google.common.collect.ImmutableMap;


import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TokenAuthenticationService implements UserAuthenticationService {
    @NonNull
    TokenService tokens;
    @NonNull
    UserService userService;

    @Override
    public AuthResponseDto login(final String username, final String password, final Integer otpCode) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user =   userService
                .findByUsername(username)
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .orElseThrow(() -> new RuntimeException("INVALID_USERNAME_OR_PASSWORD : invalid login and/or password"));

        // Return JWT token
        return  AuthResponseDto.builder().jwt(tokens.expiring(user)).build();

    }

    @Override
    public Optional<User> findByToken(final String token) {
        return Optional
                .of(tokens.verify(token))
                .map(map -> map.get("user"))
                .flatMap(userService::findByUsername);
    }

    @Override
    public void logout(final User user) {
        // Nothing to doy
    }


}