package com.citt.wellmart.services.jwt;

import com.citt.wellmart.entities.security.User;

import java.util.Map;

/**
 * Creates and validates credentials.
 */
public interface TokenService {

    String permanent(User user);

    String expiring(User user);

    /**
     * Checks the validity of the given credentials.
     *
     * @param token
     * @return attributes if verified
     */
    Map<String, String> untrusted(String token);

    /**
     * Checks the validity of the given credentials.
     *
     * @param token
     * @return attributes if verified
     */
    Map<String, String> verify(String token);
}
