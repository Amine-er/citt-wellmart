package com.citt.wellmart.controller.mappers;


import com.citt.wellmart.controller.models.AuthorityDto;
import com.citt.wellmart.entities.security.Authority;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AuthorityMapper {
    Authority authorityDtoToAuthority(AuthorityDto authorityDto);
    AuthorityDto authorityToAuthorityDto(Authority authority);
    List<Authority> authoritiesDtoToAuthorities(List<AuthorityDto> authoritiesDtos);
}
