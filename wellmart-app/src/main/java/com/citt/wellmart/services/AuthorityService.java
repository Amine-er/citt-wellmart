package com.citt.wellmart.services;


import com.citt.wellmart.controller.models.AuthorityDto;

import java.util.List;
import java.util.UUID;

public interface AuthorityService {
    List<AuthorityDto> getAllAuthorities();
    AuthorityDto getAuthorityById(UUID uuid);
    void deleteAuthorityById(UUID uuid);
    void updateAuthority(AuthorityDto authorityDto);
    AuthorityDto saveAuthority(AuthorityDto authorityDto);
}
