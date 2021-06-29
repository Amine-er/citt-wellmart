package com.citt.wellmart.services;




import com.citt.wellmart.controller.models.RoleDto;
import com.citt.wellmart.controller.models.UserDto;
import com.citt.wellmart.entities.security.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(UUID uuid);
    void deleteUserById(UUID uuid);
    void updateUser(UserDto userDto) throws Exception;
    UserDto saveUser(UserDto userDto) throws Exception;
    Set<RoleDto> getUserRolesByUserId(UUID uuid);
    void addRolesToUser(UUID uuid,List<RoleDto> roles);
    Optional<User> find(UUID id);
    Optional<User> findByUsername(String username);
    void setUseGoogle2Fa(String username);
}
