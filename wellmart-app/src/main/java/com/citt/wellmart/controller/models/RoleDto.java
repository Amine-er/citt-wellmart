package com.citt.wellmart.controller.models;

import lombok.*;

import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private UUID id;
    private String name;
    private Set<AuthorityDto> authorities ;
}
