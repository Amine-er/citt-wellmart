package com.citt.wellmart.services.impl;



import com.citt.wellmart.controller.mappers.RoleMapper;
import com.citt.wellmart.controller.mappers.UserMapper;
import com.citt.wellmart.controller.models.RoleDto;
import com.citt.wellmart.controller.models.UserDto;
import com.citt.wellmart.entities.security.Role;
import com.citt.wellmart.entities.security.User;
import com.citt.wellmart.repositories.UserRepository;
import com.citt.wellmart.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository ;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;


    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(UUID uuid) {
        // get or else throw exception not found
        return userMapper.userToUserDto(userRepository.findById(uuid).get());
    }

    @Override
    public void deleteUserById(UUID uuid) {
        userRepository.deleteById(uuid);
    }

    @Override
    public void updateUser(UserDto userDto) throws Exception {
        log.debug("Update user data ...");
        User user = userRepository.findById(userDto.getId()).orElseThrow(()->{
            return new Exception("SbsConstants.USER_NOT_FOUND_KEY,SbsConstants.USER_NOT_FOUND_MSG");
        });
        user.setUsername(userDto.getUsername());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setPhone(user.getPhone());
        user.setPassword(user.getPassword());// TODO add password hashing
        userRepository.save(user);
    }

    @Override
    public UserDto saveUser(UserDto userDto) throws Exception {
        log.debug("Saving user data ...");
        if(checkUserAlreadyExist(userDto.getUsername())){
            throw new Exception("SbsConstants.USER_ALREADY_EXIST_I18N_KEY,SbsConstants.USER_ALREADY_EXIST_MSG");
        }
        return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(hashPassword(userDto))));
    }

    @Override
    public Set<RoleDto> getUserRolesByUserId(UUID uuid) {
        // get or else throw exception not found
        return userMapper.userToUserDto(userRepository.findById(uuid).orElseThrow()).getRoles();
    }

    @Override
    public void addRolesToUser(UUID uuid, List<RoleDto> rolesDtos) {
          if(rolesDtos != null && rolesDtos.size()>0)  {
                  List<Role>  roles =   roleMapper.rolesDtoToRoles(rolesDtos);
                  User user = userRepository.getOne(uuid);   // get or else throw exception not found

                  Set<String> roleNames = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
                  List<Role> rolesToAdd = roles.stream().filter(e->!roleNames.contains(e.getName())).collect(Collectors.toList());

                  user.getRoles().addAll(rolesToAdd);
                  userRepository.save(user);
          }
    }

    @Override
    public Optional<User> find(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private boolean checkUserAlreadyExist(String userName){
        return userRepository.findByUsername(userName).isPresent() ? true:false;
    }
    private UserDto hashPassword(UserDto userDto){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDto dto = UserDto.builder()
                .address(userDto.getAddress())
                .email(userDto.getEmail())
                .enabled(userDto.isEnabled())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .useGoogle2Fa(userDto.getUseGoogle2Fa())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(userDto.getRoles())
                .build();
        return dto;
    }
    @Override
    public void setUseGoogle2Fa(String username) {
        User currentUser = userRepository.findByUsername(username).orElseThrow();
        currentUser.setGoogle2faRequired(true);
        userRepository.save(currentUser);

    }
}
