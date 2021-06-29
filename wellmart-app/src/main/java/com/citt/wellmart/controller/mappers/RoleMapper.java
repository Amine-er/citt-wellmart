package com.citt.wellmart.controller.mappers;



import com.citt.wellmart.controller.models.RoleDto;
import com.citt.wellmart.entities.security.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    Role roleDtoToRole(RoleDto roleDto);
    List<Role> rolesDtoToRoles(List<RoleDto> roleDtos);
    RoleDto roleToRoleDto(Role role);

}
