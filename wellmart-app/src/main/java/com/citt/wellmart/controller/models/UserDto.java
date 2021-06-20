package com.citt.wellmart.controller.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private  UUID id;
    @NotNull
    @Size(min=3,max=24)
    private  String username;
    private String password;
    @NotNull
    private  String firstName ;
    @NotNull
    private  String lastName ;
    @NotNull
    @Email
    private  String email ;
    private  String phone ;
    private  String address ;
    private  boolean enabled = true ;
    private Set<RoleDto> roles ;
    private Boolean useGoogle2Fa  ;
    private Boolean google2faRequired;

}
