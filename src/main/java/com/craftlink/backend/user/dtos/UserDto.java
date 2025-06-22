package com.craftlink.backend.user.dtos;

import com.craftlink.backend.user.models.UserType;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Integer id;
    private String username;
    private String authorities;
    private Set<String> offeredServices;
    private UserType userType;
}
