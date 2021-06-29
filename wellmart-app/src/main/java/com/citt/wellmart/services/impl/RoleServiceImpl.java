package com.citt.wellmart.services.impl;


import com.citt.wellmart.controller.mappers.AuthorityMapper;
import com.citt.wellmart.controller.mappers.RoleMapper;
import com.citt.wellmart.controller.models.AuthorityDto;
import com.citt.wellmart.controller.models.RoleDto;
import com.citt.wellmart.entities.security.Authority;
import com.citt.wellmart.entities.security.Role;
import com.citt.wellmart.repositories.AuthorityRepository;
import com.citt.wellmart.repositories.RoleRepository;
import com.citt.wellmart.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper ;
    private final AuthorityRepository authorityRepository ;
    private final AuthorityMapper authorityMapper ;

    @Override
    public List<RoleDto> getAllRoles() {
         return roleRepository.findAll().stream().map(roleMapper::roleToRoleDto).collect(Collectors.toList());
    }

    @Override
    public RoleDto getRoleById(UUID uuid) {
        // get or else throw exception not found
        return roleMapper.roleToRoleDto(roleRepository.findById(uuid).get());
    }

    @Override
    public void deleteRoleById(UUID uuid) {
        roleRepository.deleteById(uuid);
    }

    @Override
    public void updateRole(RoleDto roleDto) {
        roleRepository.save(roleMapper.roleDtoToRole(roleDto));
    }

    @Override
    public RoleDto saveRole(RoleDto roleDto) {
        return roleMapper.roleToRoleDto(roleRepository.save(roleMapper.roleDtoToRole(roleDto)));
    }
    @Override
    public void addAuthorityToRole(UUID uuid, List<AuthorityDto> authoritiesDtos) {
        if(authoritiesDtos != null && authoritiesDtos.size()>0)  {
            List<Authority>  authorities =   authorityMapper.authoritiesDtoToAuthorities(authoritiesDtos);
            Role role = roleRepository.getOne(uuid);   // get or else throw exception not found

            Set<String> authoritiesNames = role.getAuthorities().stream().map(Authority::getPermission).collect(Collectors.toSet());

            List<Authority> authoritiesToAdd = authorities.stream().filter(e->!authoritiesNames.contains(e.getPermission())).collect(Collectors.toList());

            role.getAuthorities().addAll(authoritiesToAdd);
            roleRepository.save(role);
        }
    }
}
