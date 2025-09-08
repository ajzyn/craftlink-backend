package com.craftlink.backend.user.application.dto;

import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import java.util.UUID;

public record UserView(
    UUID id,
    String email,
    String authorities,
    UserType userType
) {

}
