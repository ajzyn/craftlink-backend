package com.craftlink.backend.auth.application.port.in.query.getUserProfile;

import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import java.util.Set;
import java.util.UUID;

public record UserView(UUID id,
                       String email,
                       UserType userType,
                       Set<String> authorities) {

}
