package com.citt.wellmart.services;

import com.citt.wellmart.controller.models.AuthorityDto;
import com.citt.wellmart.controller.models.RoleDto;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    List<RoleDto> getAllRoles();
    RoleDto getRoleById(UUID uuid);
    void deleteRoleById(UUID uuid);
    void updateRole(RoleDto roleDto);
    RoleDto saveRole(RoleDto roleDto);
    void addAuthorityToRole(UUID uuid, List<AuthorityDto> authoritiesDtos);
}
