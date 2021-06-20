package com.citt.wellmart.controller.mappers;

import com.citt.wellmart.controller.models.UserDto;
import com.citt.wellmart.entities.security.User;
import org.mapstruct.Mapper;

@Mapper(uses = {RoleMapper.class,AuthorityMapper.class})
public interface UserMapper {
    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}
