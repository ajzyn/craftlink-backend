package com.craftlink.backend.auth.application.dto;

import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import java.util.Set;
import java.util.UUID;

public record UserAuthView(UUID id,
                           UUID clientId,
                           UUID specialistId,
                           String email,
                           String password,
                           UserType userType,
                           Set<String> authorities,
                           Set<String> offeredServices) {

}
