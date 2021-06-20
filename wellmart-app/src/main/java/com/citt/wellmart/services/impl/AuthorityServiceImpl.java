package com.citt.wellmart.services.impl;



import com.citt.wellmart.controller.mappers.AuthorityMapper;
import com.citt.wellmart.controller.models.AuthorityDto;
import com.citt.wellmart.repositories.AuthorityRepository;
import com.citt.wellmart.services.AuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository ;
    private final AuthorityMapper authorityMapper ;

    @Override
    public List<AuthorityDto> getAllAuthorities() {
        return authorityRepository.findAll().stream().map(authorityMapper::authorityToAuthorityDto).collect(Collectors.toList());
    }

    @Override
    public AuthorityDto getAuthorityById(UUID uuid) {
        // get or else throw exception not found
        return authorityMapper.authorityToAuthorityDto(authorityRepository.findById(uuid).get());
    }

    @Override
    public void deleteAuthorityById(UUID uuid) {
        authorityRepository.deleteById(uuid);
    }

    @Override
    public void updateAuthority(AuthorityDto authorityDto) {
            authorityRepository.save(authorityMapper.authorityDtoToAuthority(authorityDto));
    }

    @Override
    public AuthorityDto saveAuthority(AuthorityDto authorityDto) {
        return authorityMapper.authorityToAuthorityDto(authorityRepository.save(authorityMapper.authorityDtoToAuthority(authorityDto)));
    }

}
